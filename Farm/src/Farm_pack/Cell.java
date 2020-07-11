/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Farm_pack;

import java.awt.*;
import java.io.*;
import javax.imageio.*;

/**
 *Класс клеток фермы
 */
public class Cell extends Object{
    
    private int _is_floor; //флаг разновидновидности ячейки ( 1 -пол, 0 -грядка)
    private int _is_empty; //флаг пустоты ячейки ( 0 -пустая, 1 -грядка)
    private Image _image;  // изображение
    
    /*
    *конструктор
    *x -абцис ячейки
    *y - ординат ячейки
    *map - карта на которой рисуем
    *is_floor - флаг типа
    */
    public Cell(int x, int y, Map map,int is_floor) {
        super(x, y, map);
        this._is_floor = is_floor;
        
        if(is_floor == 1){
            try{
                this._image = ImageIO.read(new File("src/graphic arts/floor.png"));
            }
            catch(IOException e){
            }
        }
        else{
           try{
                this._image = ImageIO.read(new File("src/graphic arts/garden_bed.png"));
            }
            catch(IOException e){
            } 
        }     
    }
    
    /*метод шага жизни*/
    @Override
    public void liveStep(){}

    /*метод обозначения типа ячейки*/
    protected void set_is_floor(int is_floor){
        this._is_floor = is_floor;
    }
    
    /*метод обозначения пустоты*/
    protected void set_is_empty(int is_empty){
        this._is_empty = is_empty;
    }
    
    /*метод обозначения типа ячейки*/
    public int get_is_floor(){
        return this._is_floor;
    }
    
    /*метод обозначения пустоты*/
    public int get_is_empty(){
        return this._is_empty;
    }

    /*метод возврата картинки*/
    public  Image get_image(){
        return this._image;
    }
    
   
}
