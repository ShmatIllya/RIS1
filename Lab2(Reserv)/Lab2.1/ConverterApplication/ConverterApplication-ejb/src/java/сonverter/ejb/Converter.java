/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package �onverter.ejb;

import java.math.BigDecimal;
import javax.ejb.Stateless;

/**
 *
 * @author Notebook
 */
@Stateless
public class Converter implements ConverterLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private BigDecimal yenRate = new BigDecimal("83.0602");
private BigDecimal euroRate = new BigDecimal("0.0093016");
public BigDecimal dollarToYen(BigDecimal dollars) {
BigDecimal result = dollars.multiply(yenRate);
return result.setScale(2, BigDecimal.ROUND_UP);
}
public BigDecimal yenToEuro(BigDecimal yen) {
BigDecimal result = yen.multiply(euroRate);
return result.setScale(2, BigDecimal.ROUND_UP);
}

}
