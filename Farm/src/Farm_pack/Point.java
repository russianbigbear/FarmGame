/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Farm_pack;
import java.lang.Object;

public class Point {
    private int x;  // координата x
    private int y;  // координата y

    /*конструктор*/
    public Point(int x, int y) {
        this.x=x;
        this.y=y;
    }
    /*задать x*/
    public void set_x(int a) {
        x = a;		
    }
    /*задать y*/
    public void set_y(int b) {
        y = b;
    }
    /*получить x*/
    public int get_x() {
        return x;		
    }
    /*получить y*/
    public int get_y() {
        return y;
    }
    /*проверка истинности объектов*/
    @Override
    public boolean equals(Object o){
            if(!(o instanceof Point)) return false;
                    return (((Point)o).get_x()==x) &&(((Point)o).get_y()==y);
    }

    @Override
    public int hashCode(){
            return Integer.valueOf(x) ^ Integer.valueOf(y);
    }
    /*переопределение преобразования к String*/
    @Override
    public String toString(){
            return "x: "+Integer.valueOf(x).toString()+" y:"+Integer.valueOf(y).toString();
    }
}
