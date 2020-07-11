/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Farm_pack;

/**
 * Объект на карте
 */
public abstract class Object {
   
    private int _x; //абсцисса
    private int _y; //ордината
    private final Map _map; // ссылка на карту

    
    /**
     *кКонструктор
     * @param map ссылка на объект карты
     * @param x абсцисса
     * @param y ордината
     */
    public Object(int x, int y, Map map){
        this._map = map;
        set_x(x);
        set_y(y);
    }
    
    /* шаг жизни объекта*/
    public abstract void liveStep();

    /*установка x */
    protected void set_x(int x) {
       if (x >= this._map.get_width())
           this._x = this._map.get_width()- 1;
       else if (x < 0)
           this._x = 0;
       else
           this._x = x;
    }

    /* установка y*/
    protected void set_y(int y) {
       if (y >= this._map.get_height())
           this._y = this._map.get_height()-1;
       else if (y < 0)
           this._y = 0;
       else
           this._y = y;
    }
    
    /*возврат абсциссы */
    public int get_x(){
        return this._x;
    }

    /*возврат ординаты */    
    public int get_y(){
        return this._y;
    }
    
    /*возврат ссылки на карту*/
    protected Map getMap() {
        return this._map;
    }
}
    
