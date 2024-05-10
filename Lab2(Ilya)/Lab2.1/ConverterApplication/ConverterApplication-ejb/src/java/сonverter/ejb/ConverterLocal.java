/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package ñonverter.ejb;

import java.math.BigDecimal;
import javax.ejb.Local;

/**
 *
 * @author Notebook
 */
@Local
public interface ConverterLocal {
    public boolean Action(String input, String sits);
    public BigDecimal yenToEuro(BigDecimal yen);
}
