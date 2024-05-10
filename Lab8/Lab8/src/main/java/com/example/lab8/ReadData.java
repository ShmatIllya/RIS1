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
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
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
    sb = new StringBuilder();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            List list = session.createQuery("from Developer").list();
            sb.append("Все пользователи: ").append(list.size()).append("<br><br>");
            for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
                Developer devs = (Developer) iterator.next();
                sb.append("</br></br>Имя: ").append(devs.getFirstName());
                sb.append(";");
                sb.append("</br>Фамилия: ").append(devs.getLastName()).append(";");
                sb.append("</br>Отчество: ").append(devs.getOtchestvo()).append(";");
                sb.append("</br>Дата рождения: ").append(devs.getBirthDate()).append(";");
                sb.append("</br>Серия паспорта: ").append(devs.getPassportSeries()).append(";");
                sb.append("</br>Номер паспорта: ").append(devs.getPassportNumber()).append(";");
                sb.append("</br>Город проживания: ").append(devs.getHomeTown()).append(";");
                sb.append("</br>Адрес проживания: ").append(devs.getFactAdress()).append(";");
                sb.append("</br>Телефон: ").append(devs.getPhone()).append(";");
                sb.append("</br>Email: ").append(devs.getEmail()).append(";");
                sb.append("</br>Статус работы: ").append(devs.getWorkStatus()).append(";");
                sb.append("</br>Должность: ").append(devs.getHierarchyStatus()).append(";");
                sb.append("</br>Адрес прописки: ").append(devs.getPropiskaAdress()).append(";");
                sb.append("</br>Гражданство: ").append(devs.getCitizenship()).append(";");
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
        if ("lists".equals(lib.getTagName())) {
            NodeList customers = lib.getElementsByTagName("customers");
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
                    } else if ("otchestvo".equals(prop.getTagName())) {
                        temp.setOtchestvo(prop.getTextContent());
                    } else if ("birthDate".equals(prop.getTagName())) {
                        temp.setBirthDate(Date.from(Instant.parse(prop.getTextContent())));
                    } else if ("passportSeries".equals(prop.getTagName())) {
                        temp.setPassportSeries(prop.getTextContent());
                    } else if ("passportNumber".equals(prop.getTagName())) {
                        temp.setPassportNumber(prop.getTextContent());
                    } else if ("homeTown".equals(prop.getTagName())) {
                        temp.setHomeTown(prop.getTextContent());
                    } else if ("factAdress".equals(prop.getTagName())) {
                        temp.setFactAdress(prop.getTextContent());
                    } else if ("phone".equals(prop.getTagName())) {
                        temp.setPhone(prop.getTextContent());
                    } else if ("email".equals(prop.getTagName())) {
                        temp.setEmail(prop.getTextContent());
                    } else if ("workStatus".equals(prop.getTagName())) {
                        temp.setWorkStatus(Boolean.getBoolean(prop.getTextContent()));
                    } else if ("hierarchyStatus".equals(prop.getTagName())) {
                        temp.setHierarchyStatus(prop.getTextContent());
                    } else if ("propiskaAdress".equals(prop.getTagName())) {
                        temp.setPropiskaAdress(prop.getTextContent());
                    } else if ("citizenship".equals(prop.getTagName())) {
                        temp.setCitizenship(prop.getTextContent());
                    }

                }

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
        sb = new StringBuilder();
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
                sb.append("</br>Отчество: ").append(devs.get(i).getOtchestvo()).append(";");
                sb.append("</br>Дата рождения: ").append(devs.get(i).getBirthDate()).append(";");
                sb.append("</br>Серия паспорта: ").append(devs.get(i).getPassportSeries()).append(";");
                sb.append("</br>Номер паспорта: ").append(devs.get(i).getPassportNumber()).append(";");
                sb.append("</br>Город проживания: ").append(devs.get(i).getHomeTown()).append(";");
                sb.append("</br>Адрес проживания: ").append(devs.get(i).getFactAdress()).append(";");
                sb.append("</br>Телефон: ").append(devs.get(i).getPhone()).append(";");
                sb.append("</br>Email: ").append(devs.get(i).getEmail()).append(";");
                sb.append("</br>Статус работы: ").append(devs.get(i).getWorkStatus()).append(";");
                sb.append("</br>Должность: ").append(devs.get(i).getHierarchyStatus()).append(";");
                sb.append("</br>Адрес прописки: ").append(devs.get(i).getPropiskaAdress()).append(";");
                sb.append("</br>Гражданство: ").append(devs.get(i).getCitizenship()).append(";");
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
        sb = new StringBuilder();
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
                sb.append("</br></br>Имя: ").append(devs.get(i).getFirstName());
                sb.append(";");
                sb.append("</br>Фамилия: ").append(devs.get(i).getLastName()).append(";");
                sb.append("</br>Отчество: ").append(devs.get(i).getOtchestvo()).append(";");
                sb.append("</br>Дата рождения: ").append(devs.get(i).getBirthDate()).append(";");
                sb.append("</br>Серия паспорта: ").append(devs.get(i).getPassportSeries()).append(";");
                sb.append("</br>Номер паспорта: ").append(devs.get(i).getPassportNumber()).append(";");
                sb.append("</br>Город проживания: ").append(devs.get(i).getHomeTown()).append(";");
                sb.append("</br>Адрес проживания: ").append(devs.get(i).getFactAdress()).append(";");
                sb.append("</br>Телефон: ").append(devs.get(i).getPhone()).append(";");
                sb.append("</br>Email: ").append(devs.get(i).getEmail()).append(";");
                sb.append("</br>Статус работы: ").append(devs.get(i).getWorkStatus()).append(";");
                sb.append("</br>Должность: ").append(devs.get(i).getHierarchyStatus()).append(";");
                sb.append("</br>Адрес прописки: ").append(devs.get(i).getPropiskaAdress()).append(";");
                sb.append("</br>Гражданство: ").append(devs.get(i).getCitizenship()).append(";");
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
        sb = new StringBuilder();
        Session session= HibernateUtil.getSessionFactory().openSession();
        EntityManager em;
        Transaction tx=null;
        StringBuilder sb=new StringBuilder();
        sb.append("<h1> Были удалены следующие записи: </h1>");
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
                sb.append("</br></br>Имя: ").append(devs.get(i).getFirstName());
                sb.append(";");
                sb.append("</br>Фамилия: ").append(devs.get(i).getLastName()).append(";");
                sb.append("</br>Отчество: ").append(devs.get(i).getOtchestvo()).append(";");
                sb.append("</br>Дата рождения: ").append(devs.get(i).getBirthDate()).append(";");
                sb.append("</br>Серия паспорта: ").append(devs.get(i).getPassportSeries()).append(";");
                sb.append("</br>Номер паспорта: ").append(devs.get(i).getPassportNumber()).append(";");
                sb.append("</br>Город проживания: ").append(devs.get(i).getHomeTown()).append(";");
                sb.append("</br>Адрес проживания: ").append(devs.get(i).getFactAdress()).append(";");
                sb.append("</br>Телефон: ").append(devs.get(i).getPhone()).append(";");
                sb.append("</br>Email: ").append(devs.get(i).getEmail()).append(";");
                sb.append("</br>Статус работы: ").append(devs.get(i).getWorkStatus()).append(";");
                sb.append("</br>Должность: ").append(devs.get(i).getHierarchyStatus()).append(";");
                sb.append("</br>Адрес прописки: ").append(devs.get(i).getPropiskaAdress()).append(";");
                sb.append("</br>Гражданство: ").append(devs.get(i).getCitizenship()).append(";");
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
        sb = new StringBuilder();
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        List list = session.createQuery("from Developer").list();
        tx.commit();
        XmlWrite(list,"D:/FCP/SEM6/Rice/Lab8/xmlfile.xml");
        //JSONWrite(list, "D:/FCP/SEM6/Rice/Lab8/jsonfile.json");
        session.close();
        sb.append("<h1>Saved</h1>");
        return sb;
    }
}