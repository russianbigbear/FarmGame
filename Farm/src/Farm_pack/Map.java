/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Farm_pack;

import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Map {
    private final String _map_name; // имя карты
    private final int _height; //высота карты
    private final int _width; //щирина карты
    private final ArrayList<Object> _objects = new ArrayList<>(); // массив объектов н карте
    private int _spray_count; //количество дихлофоса
    private int _day_count; //количество дней, которые продержался игрок
    private int _money; //капитал Петровича
    private int _step_count; //пройденно шагов "жизни"
    private int _parasites_count; // общее количество паразитов
    private int _killed_parasites_count; //количество убитых паразитов

    /*
    * конструтор
    * height - высота карты
    * width -ширина карты
    */
   public Map(int height, int width, String map_name){
       this._map_name = map_name; 
        this._height = height;
        this._width = width;
        this._day_count = 0;
        this._spray_count = 0;
        this._step_count = 0;
        this._money = 10000;
        this._parasites_count = 0;
        this._killed_parasites_count = 0;
        GenerateMap();
        born_petrovich();
        
    }
   
   /*метод формирования начальной карты*/
   private void GenerateMap() {
       Scanner sc = null;
        try {
            sc = new Scanner(new File("src/Maps/" + get_map_name() + ".txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        Cell c = null;
        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 16; j++){
                c = new Cell(j, i, this, sc.nextInt());
                get_objects().add(c);
            }
        }
    }
   
    /*один шаг жизни игры*/
    public void live_step() {
    game_over();    
        
    set_step_count(get_step_count() + 1);
    
    if(get_step_count() % 60 == 0){
        set_day_count(get_day_count() + 1);
        born_parasites();      
    }

    for (int i = 0; i < get_objects().size(); i++)    
        get_objects().get(i).liveStep();
    
    }
    
    /*генерация Петровича*/
    private void born_petrovich(){
        get_objects().add(new Petrovich(0, 7, this));
    }
    
    /*метод по добавлению грядки на карту*/
    public boolean plant_a_garden_bed(int x, int y, int id_vegetables, int vegetable_price){      
        for (Object obj : get_objects()){
           if(obj.get_x() == x && obj.get_y() == y){ 
               if(obj instanceof Cell){
                   Cell c = (Cell)obj;
                   if(c.get_is_empty() == 0 && c.get_is_floor() == 0){
                        set_money(get_money() - vegetable_price);
                        c.set_is_empty(1);
                        switch(id_vegetables){
                            case 1: {get_objects().add(new Vegetables(x, y, this, "carrot", 60));break;}
                            case 2: {get_objects().add(new Vegetables(x, y, this, "cucumber", 30));break;}
                            case 3: {get_objects().add(new Vegetables(x, y, this, "eggplant", 90));break;}
                            case 4: {get_objects().add(new Vegetables(x, y, this, "potato", 120));break;}
                            case 5: {get_objects().add(new Vegetables(x, y, this, "tomato", 60));break;}
                            default: break;
                        }
                        return true;
                    }
               }      
           }       
        }
        return false;
    
    }
    
    /*генерация жуков на грядках*/
    public void born_parasites(){
         for(int i = 0; i < get_objects().size(); i++){
             if(get_objects().get(i) instanceof Vegetables){
                Vegetables v = (Vegetables) get_objects().get(i);
                if(v.get_grow_stage() == 4){
                    get_objects().add(new Parasites(v.get_x(), v.get_y(), this));
                    set_parasites_count(get_parasites_count() + 1);
                }
            }
        }
    }
    
    /*удаление объекта жуки*/
    public void remove_object_parisetes(Object obj){
        get_objects().remove(obj);
        set_parasites_count(get_parasites_count() - 1);
        set_killed_parasites_count(get_killed_parasites_count() + 1);
    }
    
    /*удаление грядки с карты*/
    public void remove_garden_bed(int x, int y) {
        for (Object obj : get_objects()){
            empty_cell(x, y, obj); 
            if(obj instanceof Vegetables) {               
                Vegetables v = (Vegetables) obj;
                if (v.get_x() == x && v.get_y() == y){
                    get_objects().remove(obj);
                    break;
                }
            }
        }
    }
    
    /*удаление паразитов с карты*/
    public void remove_parasites(int x, int y) {
        for (Object obj : get_objects()){
            if(obj instanceof Parasites) {               
                Parasites bug = (Parasites) obj;
                if (bug.get_x() == x && bug.get_y() == y){
                    remove_object_parisetes(obj);
                    break;
                }
            }
        }
    }
    
    /*удалет всех жуков и гнилых ящиков на карте*/
    public void extermination() {
        for (Object obj : get_copy_objects()){
            if(obj instanceof Parasites) {                    
                remove_object_parisetes(obj);
            }
        }
        
        for (Object obj : get_copy_objects()){
            if(obj instanceof Vegetables) {
                Vegetables v = (Vegetables) obj;
                if(v.get_grow_stage() == 4){
                    get_objects().remove(obj);
              
                    get_objects().forEach((o) -> {
                        empty_cell(v.get_x(), v.get_y(), o);
                    });
                }
            }
        }
    }
    
    /*продает все созревшие продукты на карте*/
    public void harvest(){
        for (Object obj : get_copy_objects()){
            if(obj instanceof Vegetables) {
                Vegetables v = (Vegetables) obj;          
                    if(v.get_grow_stage() == 3){ 
                        int price = 0;
                        switch(v.get_name()){
                            case "carrot": {price = 155; break;}
                            case "cucumber": {price = 125; break;}
                            case "eggplant": {price = 190; break;}
                            case "potato": {price = 220; break;}
                            case "tomato": {price = 155; break;}
                            default: break;
                        }
                                  
                        set_money(get_money() + price); 
                        
                        for(Object o : get_objects())
                            empty_cell(v.get_x(), v.get_y(), o);
                        
                        get_objects().remove(obj);
                    }
                } 
        }      
    }
    
    /*проверка на проигрыш*/
    public void game_over(){
        boolean flag_money = false;
        boolean flag_garden_bed = false;
        
        if(get_money() >= 100)
            flag_money = true;
        
        for (Object obj : get_objects()){ 
            if(obj instanceof Vegetables) {               
                Vegetables v = (Vegetables) obj;
                if (!(v.get_grow_stage() == 4)){
                    flag_garden_bed = true;
                }
            }
        }
        
        if(!flag_money && !flag_garden_bed){
            JOptionPane.showMessageDialog(null,
                    new String[] {"Вы продержались: " + get_day_count() + " дней.",
                        "Вы убили: " + get_killed_parasites_count() + " жуков."},
                     "Доктор",JOptionPane.CLOSED_OPTION);
            
            System.exit(0);
        } 
    }
    
    /*освобождени Cell*/
    public void empty_cell(int x, int y, Object obj){
        if(obj instanceof Cell){
                Cell c = (Cell) obj;
                if (c.get_x() == x && c.get_y() == y){
                    c.set_is_empty(0);
                }
        }
    }
    
    /* находит объекты в радиусе видимости
     * _object объект, для которого ищем
     * _radius радиус зрения объекта 
     */
    public ArrayList<Object> get_visible_objects(Object object, int radius){
        ArrayList<Object> visible_objects = new ArrayList<>();
        for (Object obj : get_objects()) {
            if (destination(object,obj) < radius *radius)
                visible_objects.add(obj);
        }
        return visible_objects;
    }
    
    /* вычисление расстояния между объектами
     * _object1 первый объект
     * _object2 второй объект 
     */
    private int destination(Object object_1, Object object_2) {
        return (object_1.get_x() - object_2.get_x())*(object_1.get_x() - object_2.get_x()) + (object_1.get_y() - object_2.get_y())*(object_1.get_y() - object_2.get_y());
    }
    
    /*возврат копии списка объектов на карте */
    public ArrayList<Object> get_copy_objects(){
        ArrayList<Object> copy_list = new ArrayList<>();
        copy_list.addAll(get_objects());
        return copy_list;
    }
    
    /*возврат наличия жуков*/
    public boolean is_parasites(){
        return _parasites_count > 0; 
    }
    
    /*установка количества дихлофоса*/
    public void set_spray_count(int spray_count){
        this._spray_count = spray_count;
    }
    
    /* установки количества дней*/
    public void set_day_count(int day_count){
        this._day_count = day_count;
    }
    
    /* установка количества шагов "жизни"*/
    public void set_step_count(int step_count){
        this._step_count = step_count;
    }
    
    /* установка количества денег*/
    public void set_money(int money){
        this._money = money;
    }
    
    /*установка количества жуков*/
    public void set_parasites_count(int parasites_count) {
        this._parasites_count = parasites_count;
    }
    
    /*установка количества  убитых паразитов*/
    public void set_killed_parasites_count(int killed_parasites_count){
        this._killed_parasites_count = killed_parasites_count;
    }
    
    /*возврат имени карты*/
    public String get_map_name(){
        return this._map_name ;
    }
    
    /*возврат количества жуков*/
    public int get_parasites_count() {
        return _parasites_count;
    }
    
    /*возврат количества  убитых паразитов*/
    public int get_killed_parasites_count(){
        return this._killed_parasites_count;
    }
    
    /*возвращат высоты*/
    public int get_height() {
        return _height;
    }

    /*возвращат ширины*/
    public int get_width() {
        return _width;
    }
    
    /*возврат количества отравы*/
    public int get_spray_count(){
        return this._spray_count;
    }
    
    /*возврат дней*/
    public int get_day_count(){
        return this._day_count;
    }
    
    /*возврат шагов "жизни"*/
    public int get_step_count(){
        return this._step_count;
    }
    
    /*возврат количества денег*/
    public int get_money(){
        return this._money;
    }
    
    /*возврат масива объектов*/
    public ArrayList<Object> get_objects(){
        return this._objects;
    }
       
}