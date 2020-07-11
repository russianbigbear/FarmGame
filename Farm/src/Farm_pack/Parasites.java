/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Farm_pack;

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parasites extends Object {
    /* радиус зрения */
    private final int sight;
    /* текущее здоровье жуков */
    private int _bug_health;
    /*скорость еды, для всех одинаковая */
    private final int _eat_step; 
    /*картинка жуков*/
    private Image _image;
    /*номер картинки в текущий момент*/
    private int _image_number;
    
    /*конструктор*/
    public Parasites(int x, int y, Map map) {
        super(x, y, map);
        this.sight = 2;
        this._bug_health = 100;
        this._eat_step = 25;
        
        try {
            this._image = ImageIO.read(new File("src/graphic arts/beetles_1.png")) ;
            
        } catch (IOException ex) {
           Logger.getLogger(Vegetables.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /* перемещение жуков к еде*/
    protected void go_to_garden_bed(Object object){
        int dx = object.get_x()- get_x();
        int dy = object.get_y()- get_y();
        dx = dx > 0 ? 1 : dx < 0 ? -1 : 0;
        dy = dy > 0 ? 1 : dy < 0 ? -1 : 0;
        set_x(get_x() + dx);
        set_y(get_y() + dy);
    }
    
    /* функция для случайного перемещения жуков, пока не найдут еду*/
    protected void walk()
    {
        Random r = new Random();
        int dx = 0;
        int dy = 0;
        while (dy == 0 && dx == 0) {
            dx = r.nextInt(3)-1;
            dy = r.nextInt(3)-1;            
        }
        set_x(get_x() + dx);
        set_y(get_y() + dy);        
    }
    
    /*один шаг жизни*/
    @Override
    public void liveStep() {
       ArrayList<Object> objects = getMap().get_visible_objects(this, get_sight());
        for (Object obj : objects){
            if (obj instanceof Vegetables) {
                if (obj.get_x()== get_x() && obj.get_y()== get_y()) {   
                    Vegetables v = (Vegetables)obj;
                    if (v.has_food()){
                        v.give_food(get_eat_step());
                        return;
                    }
                    else{
                        if(v.get_grow_stage() != 4){
                           
                            v.final_eat();
                            getMap().remove_garden_bed(obj.get_x(), obj.get_y());
                        }
                    }
                }
            }
        } 
        
        for (Object obj : objects){
            if (obj instanceof Vegetables){
                if (((Vegetables)obj).has_food()){
                    go_to_garden_bed(obj);
                    return;
                }
            }
        }
        
         try {
            if(get_image_number() == 1){
                 set_image(ImageIO.read(new File("src/graphic arts/beetles_2.png")));
                set_image_number(2);
            }
            else{
                set_image(ImageIO.read(new File("src/graphic arts/beetles_1.png")));
                set_image_number(1);
            }
            } catch (IOException ex) {
           Logger.getLogger(Vegetables.class.getName()).log(Level.SEVERE, null, ex);
        }
                   
        walk();
    }
    
     /* установка текущего здоровья жуков*/
    public void set_bug_health(int bug_health){
        this._bug_health = bug_health;
    }
    
    /*установка картинки жуков*/
    public void set_image(Image image){
         this._image = image;
    }
    
    /*установка номера картинки*/
    public void set_image_number(int image_number){
         this._image_number = image_number;
    }
    
    /*возврат радиуса видимости */
    public int get_sight() {
        return  this.sight;
    }
    /*возврат текущего здоровья жуков */
    public int get_bug_health(){
        return  this._bug_health;
    }
    
    /*скорость еды, для всех одинаковая */
    public int get_eat_step(){
        return  this._eat_step;
    }
    /*возврат картинки жуков*/
    public Image get_image(){
        return  this._image;
    }
    
    /*возврат номера картинки*/
    public int get_image_number(){
        return  this._image_number;
    }
   
}
