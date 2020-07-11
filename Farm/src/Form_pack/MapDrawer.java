package Form_pack;

import java.awt.Graphics;
import java.util.ArrayList;
import Farm_pack.Map;
import Farm_pack.Object;
import Farm_pack.Cell;
import Farm_pack.Vegetables;
import Farm_pack.Parasites;
import Farm_pack.Petrovich;

public class MapDrawer {

    /*объект, на котором рисуем*/
    private Graphics _graphics;

    /*карта, на которой рисуем*/
    private final Map _map;

    /*размер клетки на карте*/
    private static final int _cell_size = 28;

    /*
     *конструктор
     * map - это карта для отрисовки
     */
    public MapDrawer(Map map){
        this._map = map;
    }

    /*метод отрисовки карты*/
    public void  DrawMap(Graphics graphics, int width, int height){
        this._graphics = graphics;
        this._graphics.clearRect(0, 0, width, height);
        DrawObjects();      
    }

    /*метод накладывает изображение объекта на карту*/
    private void DrawObjects() {
        ArrayList<Farm_pack.Object> objects = get_map().get_copy_objects();
        for (Object obj : objects) {
            if (obj instanceof Cell){
                Cell cell = (Cell)obj;
               get_graphics().drawImage(cell.get_image(), cell.get_x()*_cell_size, cell.get_y()*_cell_size, null);
            }
        }
        for (Object obj : objects) {    
            if (obj instanceof Vegetables){
                Vegetables vegetables = (Vegetables)obj;
                get_graphics().drawImage(vegetables.get_image(), vegetables.get_x()*_cell_size, vegetables.get_y()*_cell_size, null);
            }
        }
        for (Object obj : objects) {    
            if (obj instanceof Parasites){
                Parasites parasites = (Parasites)obj;
                get_graphics().drawImage(parasites.get_image(), parasites.get_x()*_cell_size, parasites.get_y()*_cell_size, null);
            }
        }  
        for (Object obj : objects) {    
            if (obj instanceof Petrovich){
                Petrovich shef = (Petrovich)obj;
                get_graphics().drawImage(shef.get_image(), shef.get_x()*_cell_size, shef.get_y()*_cell_size, null);
               
            }
        }   
    }
    
    /*возрат объекта, на котором рисуем*/
    public Graphics get_graphics(){
        return this._graphics;
    }
    
    /*возрат ссылки на карту*/
    public Map get_map(){
        return this._map;
    }
}

