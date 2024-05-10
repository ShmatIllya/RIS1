package com.example.lab8;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonWriteContext;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import jakarta.persistence.EntityManager;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@RestController
@RequestMapping("/api")
public class ReadData {
    Session session=null;
    Transaction tx=null;
    StringBuilder sb=new StringBuilder();
@GetMapping("/read")
    public StringBuilder ReadAllData() {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from Developer").list();
            sb.append("Всего куплено товаров: ").append(list.size()).append("<br><br>");
            for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
                Developer cur = (Developer) iterator.next();
                sb.append("<b>Имя: </b>").append(cur.getFirstName());
                sb.append("<b>Фамилия: </b>").append(cur.getLastName());
                sb.append("<br>");
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            //session.close();
        }
        return sb;
    }

    public void XmlWrite(List<Developer> devs, String xmlname) {
        try {
            JAXBContext context = JAXBContext.newInstance(Developer.class, Lists.class);
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            Lists<Developer> lists = new Lists<Developer>();


            for(Developer i: devs)
                lists.getValues().add(i);
            marshaller.marshal(lists, new File(xmlname));


        } catch (Exception e) {

        }
    }

    public void JSONWrite(List<Developer> devs, String jsonname) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(jsonname), devs);
    }

    public List<Developer> XmlRead(String xmlname) throws ParserConfigurationException, IOException, SAXException {
        List<Developer> devs = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setIgnoringComments(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("file:///" + xmlname);
        Element lib = doc.getDocumentElement();
        if ("customers".equals(lib.getTagName())) {
            NodeList customers = lib.getElementsByTagName("customer");
            for (int i = 0; i < customers.getLength(); ++i) {
                Element customer = (Element) customers.item(i);
                String id = customer.getAttribute("id");
                Developer temp = new Developer();
                temp.setId(Integer.parseInt(id));
                NodeList props = customer.getElementsByTagName("*");
                for (int j = 0; j < props.getLength(); ++j) {
                    Element prop = (Element) props.item(j);
                    if ("firstName".equals(prop.getTagName())) {
                        System.out.println(prop.getTextContent());
                        temp.setFirstName(prop.getTextContent());
                    } else if ("lastName".equals(prop.getTagName())) {
                        temp.setLastName(prop.getTextContent());
                    } else if ("address".equals(prop.getTagName())) {
                        temp.setAddress(prop.getTextContent());
                    } else if ("budget".equals(prop.getTagName())) {
                        temp.setBudget(Integer.parseInt(prop.getTextContent()));
                    }

                }
                System.out.println(temp.getId());
                devs.add(temp);
            }
        }
        return devs;
    }

    public List<Developer> JSONRead(String jsonname) throws ParseException, IOException, JSONException {
    List<Developer> devs = new ArrayList<>();

                BufferedReader file = new BufferedReader(new FileReader(jsonname));
                String line = file.readLine();
        String jsonString = "";
                while (line!=null) {
                    jsonString+=line;
                    line = file.readLine();
                }
        ObjectMapper mapper = new ObjectMapper();
        Developer[] jsonarr = mapper.readValue(jsonString, Developer[].class);


        for(int i = 0; i < jsonarr.length;i++)
        {
            devs.add(jsonarr[i]);
        }
        return devs;
    }
    @GetMapping("/insert")
    public StringBuilder  InsertData()
    {

        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction tx=null;
        StringBuilder sb=new StringBuilder();
        sb.append("<h1> Были добавлены следующие записи: </h1>");
        Integer resId=null;
        try{
            tx=session.beginTransaction();
            List<Developer> devs = JSONRead("D:/FCP/SEM6/Rice/Lab8/jsonfile.json");
            //resId=(Integer)session.save(h);
            for(int i = 0; i < devs.size(); i++) {
//Сохранение нового объекта tx.commit();
                System.out.println(devs.get(i).getId());
                devs.get(i).setId(null);
                session.persist(devs.get(i));
                sb.append("</br></br>Имя: ").append(devs.get(i).getFirstName());
                sb.append(";");
                sb.append("</br>Фамилия: ").append(devs.get(i).getLastName()).append(";");
                sb.append("</br>Адрес: ").append(devs.get(i).getAddress()).append(";");
                sb.append("</br>Бюджет: ").append(devs.get(i).getBudget()).append(";");
            }
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();} catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally{
            tx.commit();
            session.close();
        }
        return sb;
    }

    @GetMapping("/update")
    public StringBuilder  UpdateData()
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        EntityManager em;
        Transaction tx=null;
        StringBuilder sb=new StringBuilder();
        sb.append("<h1> Были обновлены следующие записи: </h1>");
        Integer resId=null;
        try{
            tx=session.beginTransaction();
            List<Developer> devs = XmlRead("D:/FCP/SEM6/Rice/Lab8/xmlfile.xml");
            //resId=(Integer)session.save(h);
            for(int i = 0; i < devs.size(); i++) {
//Сохранение нового объекта tx.commit();
                System.out.println(devs.get(i).getId());
                session.merge(devs.get(i));
                sb.append("</br></br>ID: ").append(devs.get(i).getId()).append(";");
                sb.append("</br>Имя: ").append(devs.get(i).getFirstName());
                sb.append(";");
                sb.append("</br>Фамилия: ").append(devs.get(i).getLastName()).append(";");
                sb.append("</br>Адрес: ").append(devs.get(i).getAddress()).append(";");
                sb.append("</br>Бюджет: ").append(devs.get(i).getBudget()).append(";");
            }
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();} catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } finally{
            tx.commit();
            session.close();
        }
        return sb;
    }

    @GetMapping("/delete")
    public StringBuilder  DeleteData()
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        EntityManager em;
        Transaction tx=null;
        StringBuilder sb=new StringBuilder();
        sb.append("<h1> Были обновлены следующие записи: </h1>");
        Integer resId=null;
        try{
            tx=session.beginTransaction();
            List<Developer> devs = XmlRead("D:/FCP/SEM6/Rice/Lab8/xmlfile.xml");
            //resId=(Integer)session.save(h);
            for(int i = 0; i < devs.size(); i++) {
//Сохранение нового объекта tx.commit();
                System.out.println(devs.get(i).getId());
                session.remove(devs.get(i));
                sb.append("</br></br>ID: ").append(devs.get(i).getId()).append(";");
                sb.append("</br>Имя: ").append(devs.get(i).getFirstName());
                sb.append(";");
                sb.append("</br>Фамилия: ").append(devs.get(i).getLastName()).append(";");
                sb.append("</br>Адрес: ").append(devs.get(i).getAddress()).append(";");
                sb.append("</br>Бюджет: ").append(devs.get(i).getBudget()).append(";");
            }
        }catch(HibernateException e){
            if(tx!=null)tx.rollback();
            e.printStackTrace();} catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } finally{
            tx.commit();
            session.close();
        }
        return sb;
    }

    @GetMapping("/save")
    public StringBuilder  SaveData() throws IOException {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        List list = session.createQuery("from Developer").list();
        tx.commit();
        //XmlWrite(list,"D:/FCP/SEM6/Rice/Lab8/xmlfile.xml");
        JSONWrite(list, "D:/FCP/SEM6/Rice/Lab8/jsonfile.json");
        session.close();
        sb.append("<h1>Saved</h1>");
        return sb;
    }
}