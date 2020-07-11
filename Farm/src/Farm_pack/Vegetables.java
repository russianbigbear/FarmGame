/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Farm_pack;

import java.awt.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.*;

public class Vegetables extends Object{

    /*имя овоща*/
    private String _name;
    /*время жизни овощей на грядке*/
    private  int _lifetime;
    /*время его роста*/
    private int _growth_time;
    /* Текущее здоровье грядки */
    private int _garden_health;
    /* стадия созревания (1-5) */
    private int _grow_stage;
    /*картинка овоща*/
    private Image _image;
    
    /*конструктор*/
    public Vegetables(int x, int y, Map map, String name, int growth_time) {
        super(x, y, map);
        this._name = name;
        this._lifetime = 0;
        this._growth_time = growth_time;
        this._garden_health = 100;
        this._grow_stage = 0;
        
        try {
            this._image = ImageIO.read(new File("src/graphic arts/seeds.png"));
        } catch (IOException ex) {
           Logger.getLogger(Vegetables.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*один шаг жизни*/   
    @Override
    public void liveStep() {
        set_lifetime(get_lifetime() + 1);   
        grow();       
    }

    /*анимация созревания овоща*/
    public void grow(){
        if(get_garden_health() != 0)
        switch(get_lifetime()/get_growth_time()){ 
            case 1: {
                try {
                    String tmp = "src/graphic arts/" + get_name() + "_1_" + get_garden_health() + ".png";
                    set_image(ImageIO.read(new File(tmp)));
                    set_grow_stage(1);
                } catch (IOException ex) {
                    Logger.getLogger(Vegetables.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case 2: {
                try {
                    String tmp = "src/graphic arts/" + get_name() + "_2_" + get_garden_health() + ".png";
                    set_image(ImageIO.read(new File(tmp)));
                    set_grow_stage(2);
                } catch (IOException ex) {
                    Logger.getLogger(Vegetables.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case 3: {
                try {
                    String tmp = "src/graphic arts/" + get_name() + "_3_" + get_garden_health() + ".png";
                    set_image(ImageIO.read(new File(tmp)));
                    set_grow_stage(3);
                } catch (IOException ex) {
                    Logger.getLogger(Vegetables.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case 4: {
                try {
                    set_image(ImageIO.read(new File("src/graphic arts/rotten_box.png")));
                    set_grow_stage(4);
                } catch (IOException ex) {
                    Logger.getLogger(Vegetables.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            default: break;
        }     
    }
    /*анимация поедания продуктов паразитами*/
    public void eatten_veg(){
        if(get_grow_stage() != 4 && get_grow_stage() != 0){
        switch(get_garden_health()) {
            case 25: { 
                try {
                    String tmp = "src/graphic arts/" + get_name() + "_" + get_grow_stage() + "_25.png";
                    set_image(ImageIO.read(new File(tmp)));
                } catch (IOException ex) {
                    Logger.getLogger(Vegetables.class.getName()).log(Level.SEVERE, null, ex);
                }
                break; }
            case 50: {
            try {
                    String tmp = "src/graphic arts/" + get_name() + "_" + get_grow_stage() + "_50.png";
                    set_image(ImageIO.read(new File(tmp)));
                } catch (IOException ex) {
                    Logger.getLogger(Vegetables.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;}
            case 75: { 
            try {
                    String tmp = "src/graphic arts/" + get_name() + "_" + get_grow_stage() + "_75.png";
                    set_image(ImageIO.read(new File(tmp)));
                } catch (IOException ex) {
                    Logger.getLogger(Vegetables.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;}
            default: { 
                try {
                    set_image(ImageIO.read(new File("src/graphic arts/garden_bed.png")));
                } catch (IOException ex) {
                    Logger.getLogger(Vegetables.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }
     }
    }
    /*
     * отдать еду паразиту
     * foodVolume потребности в еде
     */
    public int give_food(int food_volume){
        if (get_garden_health() > food_volume) {
            set_garden_health(get_garden_health() - food_volume);
            eatten_veg();
            return food_volume;
        }
        else{
            int real_volume = get_garden_health();
            set_garden_health(0);
            eatten_veg();
            return real_volume;
        }
    }
    
    /*
     * если полностью съедено, появляется грядка
     */
    public void final_eat() {
        set_garden_health(0);
        try{
            set_image(ImageIO.read(new File("src/graphic arts/garden_bed.png")));
        }
        catch(IOException e){
        }
    }
    
    /*есть ли еще еда*/
    public boolean has_food() {
        return this._garden_health > 0;
    }
    
    /*время жизни овощей на грядке*/
    public void set_lifetime(int life){
        this._lifetime = life;
    }
    
    /*установка имени овощей*/
    public void set_name(String nm){
        this._name = nm;
    }
    
    /*установка здоровья овощей*/
    public void set_garden_health(int garden_health){
        this._garden_health = garden_health;
    }
    
    /*установка времени роста овощей*/
    public void set_growth_time(int time) {
        this._growth_time = time;
    }
    
    /*установка картинки овоща*/
    public void set_image(Image img){
        this._image = img;
    }
    
    /*установка стадии созревания (1-5) */
    public void set_grow_stage(int stage){
        this._grow_stage = stage;
    }
    
    /*возврат времени жизни овощей на грядке*/
    public int get_lifetime(){
        return this._lifetime;
    }
    
    /*возврат имя овощей*/
    public String get_name(){
        return this._name;
    }
    
    /*возврат текущего здоровья овощей */
    public int get_garden_health(){
        return this._garden_health;
    }
    
    /*вовзрат времени роста овощей*/
    public int get_growth_time(){
        return this._growth_time;
    }
    
    /*возврат стадии созревания (1-5) */
    public int get_grow_stage(){
        return this._grow_stage;
    }
    
    /*возврат картинка овощей*/
    public  Image get_image(){
        return this._image;
    }
       
}