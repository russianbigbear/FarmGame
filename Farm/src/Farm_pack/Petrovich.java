/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Farm_pack;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.*;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Petrovich extends Object {
    /* радиус зрения */
    private final int sight_1;
    private final int sight_2;
    /* изображение */
    private Image _image;
    /* карта поля */
    private final int[][] _map_array;
    /* путь между двумя объектами */
    private List<Point> _path;
    /* переменная проверки существования пути */
    private boolean _are_path;
    
    /*конструктор*/
    public Petrovich(int x, int y, Map map) {
        super(x, y, map);
        this.sight_1 = 2;
        this.sight_2 = 4;
        try {
            this._image = ImageIO.read(new File("src/graphic arts/PP_1.png"));
        } catch (IOException ex) {
           Logger.getLogger(Vegetables.class.getName()).log(Level.SEVERE, null, ex);
        }
        this._map_array = new int[16][16];
        this._path = new ArrayList<>();
        this._are_path = false;
        
        read_map(map.get_map_name()); 
        
    }

    /*чтение карты с файла в массив*/
    private void read_map(String map_name){
        Scanner sc = null; 
        try { 
            sc = new Scanner(new File("src/Maps/" + map_name + ".txt")); 
        } catch (FileNotFoundException ex) { 
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex); 
        } 
        
        for(int i = 0; i < 16; i++){ 
            for(int j = 0; j < 16; j++){ 
                int c = sc.nextInt(); 
                get_map_array()[i][j] = c; 
            } 
        }
    }
    
    /*поиск пути*/
    public void find_path(int X, int Y){
        Find_path path_Finder = new Find_path(get_map_array());
        
        Point start = new Point(get_x(), get_y());// Hачальная точка
        Point end = new Point(X, Y);//Конечная точка
        set_path(path_Finder.find(start,end)); // Hайдем путь  
        
        if(get_path().isEmpty())
            set_are_path(true);           
    }
    
    /* перемещение к обекту*/
    public void go_to_object() {

        int dx = get_path().get(0).get_x()- this.get_x();
        int dy = get_path().get(0).get_y()- this.get_y();
        dx = dx > 0 ? 1 : dx < 0 ? -1 : 0;
        dy = dy > 0 ? 1 : dy < 0 ? -1 : 0;
        one_step(dx, dy);
        
        set_x(get_path().get(0).get_x() + dx);
        set_y(get_path().get(0).get_y() + dy);
        get_path().remove(0);
    }
    
    /*
     * анимация шага Петровича
     */
    public void one_step(int dx, int dy){
         // шагает по диагонали слево направо
        if((dx == 1 && dy == 1) || (dx == -1 && dy == -1)) {
            try {
                set_image(ImageIO.read(new File("src/graphic arts/PP_3.png")));
            } catch (IOException ex) {
                Logger.getLogger(Vegetables.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // шагает по диагонали справа налево
        if((dx == 1 && dy == -1) ||(dx == -1 && dy == 1)){
            try {
                set_image(ImageIO.read(new File("src/graphic arts/PP_4.png")));
            } catch (IOException ex) {
                Logger.getLogger(Vegetables.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // шагает влево-вправо
        if(dx != 0 && dy == 0){
            try {
                set_image(ImageIO.read(new File("src/graphic arts/PP_1.png")));
            } catch (IOException ex) {
                Logger.getLogger(Vegetables.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // шагает вверх-вниз
        if(dy != 0 && dx == 0){
            try {
                set_image(ImageIO.read(new File("src/graphic arts/PP_2.png")));
            } catch (IOException ex) {
                Logger.getLogger(Vegetables.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
    /*
     * Функция для случайного перемещения 
     */
    protected boolean Walk(){
        Random r = new Random(); 
        ArrayList<Object> objects = getMap().get_visible_objects(this, get_sight_1());

        int dx = 0;
        int dy = 0;
        while (dy == 0 && dx == 0){
            dx = r.nextInt(3)-1;
            dy = r.nextInt(3)-1;            
        }   
       one_step(dx, dy);
        
        for (Object obj : objects){
            if (obj instanceof Cell){
                Cell c = (Cell)obj;
                if( (c.get_x() == get_x() + dx) && (c.get_y() == get_y() + dy) && c.get_is_floor() == 1){
                    set_x(get_x() + dx);
                    set_y(get_y() + dy); 
                    return true;
                }
            }
        } 
        return false;
    }
    
    /*шаг жизни*/
    @Override
    @SuppressWarnings("empty-statement")
    public void liveStep() {
        for (Object obj : getMap().get_visible_objects(this, get_sight_2())){
            
            if (obj instanceof Vegetables) {
                Vegetables veg = (Vegetables)obj;
                
                if(veg.get_grow_stage() == 3 || veg.get_grow_stage() == 4){ 
                    ArrayList<Object> o = new ArrayList<>(getMap().get_visible_objects(veg, 2));  
                    if(o.get(0) instanceof Cell)
                    {
                        while(((Cell)o.get(0)).get_is_floor() != 1)
                            o.remove(0);
                        if(!get_are_path())
                            find_path(o.get(0).get_x(), o.get(0).get_y());
                        else{
                            go_to_object();
                            if(get_path().isEmpty())
                                set_are_path(false);
                           return; 
                        }       
                    }                
                } 
            }
        }
        
        ArrayList<Object> objects = getMap().get_visible_objects(this, get_sight_1());
        for (Object obj : objects){
            if (obj instanceof Parasites) {
                Parasites bug = (Parasites)obj;
                if(getMap().get_spray_count() > 0){
                    getMap().remove_parasites(bug.get_x(), bug.get_y());
                    getMap().set_spray_count( getMap().get_spray_count() -  1);
                }
            }
            if (obj instanceof Vegetables){
                Vegetables veg = (Vegetables)obj;
                if(veg.get_grow_stage() == 4){
                   getMap().remove_garden_bed(veg.get_x(),veg.get_y());
                }
               
                if(veg.get_grow_stage() == 3){ 
                   int price = 0;
                   switch(veg.get_name()){
                       case "carrot": {price = 155; break;}
                       case "cucumber": {price = 125; break;}
                       case "eggplant": {price = 190; break;}
                       case "potato": {price = 220; break;}
                       case "tomato": {price = 155; break;}
                       default: break;
                   }
                   getMap().set_money(getMap().get_money() + price);
                   getMap().remove_garden_bed(veg.get_x(),veg.get_y());
                }
            }
            
        }
        
        while(!Walk());
    }
    
    /*установка картинки*/
    public void set_image(Image image){
        this._image = image;
    } 
    /*установка флага наличия пути*/
    public void set_are_path(boolean are){
        this._are_path = are;
    } 
    /*установка пути*/
    public void set_path(List<Point> path){
        this._path = path;
    }
    /*возврат радиуса видимости */
    public int get_sight_1(){
        return this.sight_1;
    } 
    public int get_sight_2(){
        return this.sight_2;
    }
     /*возврат картинки*/
    public Image get_image(){
        return this._image;
    }
     /*возврат пути*/
    public List<Point> get_path(){
        return this._path;
    }
     /*возврат флага пути*/
    public boolean get_are_path(){
        return this._are_path;
    }
    /*возврат массива карты*/
    public int[][] get_map_array(){
        return this._map_array;
    }
}