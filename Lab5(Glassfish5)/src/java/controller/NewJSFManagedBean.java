/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transactional;

/**
 *
 * @author Notebook
 */
@Named(value = "newMB")

@SessionScoped
public class NewJSFManagedBean implements Serializable {

    private static ArrayList<Customer> values = new ArrayList<>();
    private static ArrayList<Colleagues> values2 = new ArrayList<>();
    //String[] CustomerStrings = new String[12];
    String[] CustomerStrings = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
    String[] ColleagueStrings = {"0", "0", "0", "0", "0", "0"};
    private String name = "Hi";
    Customer customer = null;
    Colleagues colleagues = null;
    static int count = 0;

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("Lab5_GU");
    @PersistenceContext(unitName = "Lab5_GU")
    private EntityManager em = factory.createEntityManager();
    @Resource
    private javax.transaction.UserTransaction utx;

    // Context ctx = (Context) new InitialContext().lookup("java:comp/env");
    public void fillArrayListCustomer() throws NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
        count = 0;
        values = new ArrayList<>();
        for (Integer i = 1;  i < 100; i++) {
            
            customer = em.find(Customer.class, i);
            if(customer != null)
            {
            values.add(customer);
            }
        }

    }

    public void fillArrayListColleagues() throws NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
        count = 0;
        values2 = new ArrayList<>();
        for (Integer i = 1; i < 100; i++) {
            
            utx.begin();
            colleagues = em.find(Colleagues.class, i);
            utx.commit();
            if(colleagues != null)
            {
            values2.add(colleagues);
            }
        }

    }
    
    public static boolean isNumeric(String str) { 
  try {  
    Double.parseDouble(str);  
    return true;
  } catch(NumberFormatException e){  
    return false;  
  }  
}
    
    public String findByID(String customerNr, String choice) {
        boolean some_check = isNumeric(customerNr);
        if(some_check == false)
        {
            return "LOX";
        }
        Integer customerNr_i = Integer.valueOf(customerNr);
        try {
            if (Integer.parseInt(choice) == 1) {           
                /*em.createNativeQuery("fdsf")
                    .setParameter(1, "Tom")
                    .executeUpdate();*/
                utx.begin();
                customer = em.find(Customer.class, customerNr_i);

                utx.commit();
            } else {
                
                utx.begin();
                colleagues = em.find(Colleagues.class, customerNr_i);

                utx.commit();
                if(colleagues == null)
                {
                    return "LOX";
                }
                return "user";
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
        if(customer == null)
        {
            return "LOX";
        }
        return "hello";
    }

    public void DeleteByID(String customerNr, String choice) {
         boolean some_check = isNumeric(customerNr);
        if(some_check == false)
        {
            return;
        }
        Integer customerNr_i = Integer.valueOf(customerNr);
        try {
            if (Integer.parseInt(choice) == 1) {
                
                /*em.createNativeQuery("fdsf")
                    .setParameter(1, "Tom")
                    .executeUpdate();*/
                utx.begin();
                customer = em.find(Customer.class, customerNr_i);
                em.remove(customer);
                utx.commit();
            } else {
                
                utx.begin();
                colleagues = em.find(Colleagues.class, customerNr_i);
                em.remove(colleagues);
                utx.commit();

            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }

    }

    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public NewJSFManagedBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String moveToUser() {
        return "hello"; //outcome
    }

    public Customer getCustomer() {
        return customer;
    }

    public Colleagues getColleagues() {
        return colleagues;
    }

    public ArrayList<Customer> getValues() {
        return values;
    }

    public ArrayList<Colleagues> getValues2() {
        return values2;
    }

    public int getCount() {
        return count;
    }

    public void setCount(Object value) {
        count = (int) value;
    }

    public void setCustomerStrings(String value, int id) {
       
        CustomerStrings[id] = value;

    }
    public void setColleaguesStrings(String value, int id) {
       
        ColleagueStrings[id] = value;

    }
    public String getCustomerStrings(int id) {

       
        return "fdfsdfs";
    }
@Transactional
    public void AddCustomer() {
        customer = new Customer();
        customer.setCustomerId(Integer.valueOf(CustomerStrings[0]));
        customer.setDiscountCode(CustomerStrings[1].charAt(0));
        customer.setZip(CustomerStrings[2]);
        customer.setName(CustomerStrings[3]);
        customer.setAddressline1(CustomerStrings[4]);
        customer.setAddressline2(CustomerStrings[5]);
        customer.setCity(CustomerStrings[6]);
        customer.setState(CustomerStrings[7]);
        customer.setPhone(CustomerStrings[8]);
        customer.setFax(CustomerStrings[9]);
        customer.setEmail(CustomerStrings[10]);
        customer.setCreditLimit(Integer.valueOf(CustomerStrings[11]));
       // em.merge(customer);
        em.persist(customer);
        /*String query = "INSERT INTO customer"
                + " VALUES ( :a, :b, :c, :d, :e, :f, :g, :h, :i, :j, :k, :l)";

        em.createNativeQuery(query)
                .setParameter("a", customer.getCustomerId())
                .setParameter("b", customer.getDiscountCode())
                .setParameter("c", customer.getZip())
                .setParameter("d", customer.getName())
                .setParameter("e", customer.getAddressline1())
                .setParameter("f", customer.getAddressline2())
                .setParameter("g", customer.getCity())
                .setParameter("h", customer.getState())
                .setParameter("i", customer.getPhone())
                .setParameter("j", customer.getFax())
                .setParameter("k", customer.getEmail())
                .setParameter("l", customer.getCreditLimit())
                .executeUpdate();*/
        CustomerStrings = new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
    }
@Transactional
    public void AddColleague() {
        colleagues = new Colleagues();
        colleagues.setId(Integer.valueOf(ColleagueStrings[0]));
        colleagues.setFirstname(ColleagueStrings[1]);
        colleagues.setLastname(ColleagueStrings[2]);
        colleagues.setTitle(ColleagueStrings[3]);
        colleagues.setDepartment(ColleagueStrings[4]);
        colleagues.setEmail(ColleagueStrings[5]);
        
       // em.merge(customer);
        em.persist(colleagues);
          ColleagueStrings = new String[]{"0", "0", "0", "0", "0", "0",};
    }
    @Transactional
    public void UpdateCustomer() {
        customer = new Customer();
        
            customer = em.find(Customer.class, Integer.parseInt(CustomerStrings[0]));
            if(!"0".equals(CustomerStrings[0]))
            {
                customer.setCustomerId(Integer.valueOf(CustomerStrings[0]));
            }
            if(!"0".equals(CustomerStrings[1]))
            {
             customer.setDiscountCode(CustomerStrings[1].charAt(0));   
            }
            if(!"0".equals(CustomerStrings[2]))
            {
                customer.setZip(CustomerStrings[2]);
            }
            if(!"0".equals(CustomerStrings[3]))
            {
                customer.setName(CustomerStrings[3]);
            }
            if(!"0".equals(CustomerStrings[4]))
            {
                customer.setAddressline1(CustomerStrings[4]);
            }
            if(!"0".equals(CustomerStrings[5]))
            {
                customer.setAddressline2(CustomerStrings[5]);
            }
            if(!"0".equals(CustomerStrings[6]))
            {
                customer.setCity(CustomerStrings[6]);
            }
            if(!"0".equals(CustomerStrings[7]))
            {
                customer.setState(CustomerStrings[7]);
            }
            if(!"0".equals(CustomerStrings[8]))
            {
                customer.setPhone(CustomerStrings[8]);
            }
            if(!"0".equals(CustomerStrings[9]))
            {
                 customer.setFax(CustomerStrings[9]);
            }
            if(!"0".equals(CustomerStrings[10]))
            {
                 customer.setEmail(CustomerStrings[10]);
            }
            if(!"0".equals(CustomerStrings[11]))
            {
                customer.setCreditLimit(Integer.valueOf(CustomerStrings[11]));
            }
        
        
        
        em.merge(customer);
        /*String query = "Update customer Set discount_code = :b, zip = :c, name = :d, addressline1 = :e, addressline2 = :f, city = :g, state = :h, phone = :i, fax = :j, email = :k, credit_limit = :l Where customer_id = :a";

        em.createNativeQuery(query)
                .setParameter("a", customer.getCustomerId())
                .setParameter("b", customer.getDiscountCode())
                .setParameter("c", customer.getZip())
                .setParameter("d", customer.getName())
                .setParameter("e", customer.getAddressline1())
                .setParameter("f", customer.getAddressline2())
                .setParameter("g", customer.getCity())
                .setParameter("h", customer.getState())
                .setParameter("i", customer.getPhone())
                .setParameter("j", customer.getFax())
                .setParameter("k", customer.getEmail())
                .setParameter("l", customer.getCreditLimit())
                .executeUpdate();*/
        CustomerStrings = new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};
    }
    @Transactional
    public void UpdateColleague() {
        colleagues = new Colleagues();
        
          colleagues = em.find(Colleagues.class, Integer.valueOf(ColleagueStrings[0]));
          if(!"0".equals(ColleagueStrings[0]))
          {
            colleagues.setId(Integer.valueOf(ColleagueStrings[0]));   
          }
          if(!"0".equals(ColleagueStrings[1]))
          {
            colleagues.setFirstname(ColleagueStrings[1]);  
          }
          if(!"0".equals(ColleagueStrings[2]))
          {
             colleagues.setLastname(ColleagueStrings[2]);  
          }
          if(!"0".equals(ColleagueStrings[3]))
          {
            colleagues.setTitle(ColleagueStrings[3]);
          }
          if(!"0".equals(ColleagueStrings[4]))
          {
             colleagues.setDepartment(ColleagueStrings[4]);
          }
          if(!"0".equals(ColleagueStrings[5]))
          {
             colleagues.setEmail(ColleagueStrings[5]);
          }
        em.merge(colleagues);
        ColleagueStrings = new String[]{"0", "0", "0", "0", "0", "0",};
        //em.persist(colleagues);
    }
    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

}
