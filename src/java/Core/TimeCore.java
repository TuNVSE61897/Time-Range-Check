/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

/**
 *
 * @author LENOVO
 */
public class TimeCore {
    
    public boolean checkResult(int checkTimeHour, int checkTimeMin, int hourFrom, int minFrom, int hourTo, int minTo) {
        
        if (checkTimeHour>hourFrom && checkTimeHour<hourTo) {
            return true;
        } else if (checkTimeHour == hourFrom && checkTimeHour<hourTo) {
            if (checkTimeMin >= minFrom){
                return true;
            }
        } else if (checkTimeHour > hourFrom && checkTimeHour==hourTo) {
            if (checkTimeMin < minTo){
                return true;
            }
        } else if (checkTimeHour == hourFrom && checkTimeHour==hourTo) {
            if (checkTimeMin == minFrom && checkTimeMin == minTo){
                return true;
            }
            if (checkTimeMin >= minFrom && checkTimeMin < minTo){
                return true;
            }
        }
                
        return false;
        
    }

}
