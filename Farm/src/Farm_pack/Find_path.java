/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Farm_pack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Object;


public class Find_path {
    private int[][] fillmap = new int[16][16]; // матрица пути
    private int[][] labyrinth; // исходная матрица
    private List buf = new ArrayList(); // аналог стека для поиска подходящих точек
    
    /*конструктор*/
    public Find_path(int[][] labyrinth){
        this.labyrinth = labyrinth;
    }

    /* поиск короткого пути */
    public void push(Point p, int n){
            if(fillmap[p.get_y()][p.get_x()] <= n) return; // Если новый путь не коpоче, то он нам не нужен
            fillmap[p.get_y()][p.get_x()] = n; // Иначе запоминаем новую длину пути
            buf.add(p); // Запоминаем точку
    }

    /* достаем верзнюю точку*/
    public Point pop(){
            if(buf.isEmpty()) return null;
            return (Point)buf.remove(0);
    }

    /*поиск пути*/
    public List<Point> find(Point start, Point end){
            int tx=0, ty=0, n = 0, t=0;
            Point p;
            // Вначале fillmap заполняется max значением
            for(int i=0; i < fillmap.length;i++)
                    Arrays.fill(fillmap[i], Integer.MAX_VALUE);
            push(start, 0); // Путь в начальную точку = 0
            while((p = pop())!=null){ // Цикл, пока есть точки в буфеpе
                    // n = длина пути до любой соседней клетки
                    n = fillmap[p.get_y()][p.get_x()] + labyrinth[p.get_y()][p.get_x()];

                    //Пеpебоp 4-х соседних клеток
                    if( (p.get_y()+1 < labyrinth.length) && (labyrinth[p.get_y()+1][p.get_x()] != 0)) push(new Point(p.get_x(), p.get_y()+1), n);
                    if( (p.get_y()-1 >= 0)&& (labyrinth[p.get_y()-1][p.get_x()] != 0) ) push(new Point(p.get_x(), p.get_y()-1), n);
                    if( (p.get_x()+1 < labyrinth[p.get_y()].length) && (labyrinth[p.get_y()][p.get_x()+1] != 0)) push(new Point(p.get_x()+1, p.get_y()), n);
                    if( (p.get_x()-1 >= 0) && (labyrinth[p.get_y()][p.get_x()-1] != 0)) push(new Point(p.get_x()-1, p.get_y()), n);
                    if( (p.get_y()-1 >= 0 && p.get_x()+1 < labyrinth[p.get_y()].length) && (labyrinth[p.get_y()-1][p.get_x()+1] != 0) ) push(new Point(p.get_x()+1, p.get_y()-1), n);
                    if( (p.get_y()+1 < labyrinth.length && p.get_x()-1 >= 0) && (labyrinth[p.get_y()+1][p.get_x()-1] != 0) ) push(new Point(p.get_x()-1, p.get_y()+1), n);
                    if( (p.get_y()-1 >= 0 && p.get_x()-1 >= 0) && (labyrinth[p.get_y()-1][p.get_x()-1] != 0) ) push(new Point(p.get_x()-1, p.get_y()-1), n);
                    if( (p.get_y()+1 < labyrinth.length && p.get_x()+1 < labyrinth[p.get_y()].length) && (labyrinth[p.get_y()+1][p.get_x()+1] != 0) ) push(new Point(p.get_x()+1, p.get_y()+1), n);
            }

            List path = new ArrayList();
            path.add(end);
            int x = end.get_x();
            int y = end.get_y();
            n = Integer.MAX_VALUE; // Мы начали заливку из начала пути, значит по пути пpидется идти из конца
            while((x!=start.get_x())||(y!=start.get_y())){ // Пока не пpидем в начало пути
                    // Здесь ищется соседняя
                    if(y + 1 < labyrinth.length && fillmap[y+1][x]<n){tx=x; ty=y+1; t=fillmap[y+1][x];}
                    // клетка, содеpжащая
                    if(y - 1 >= 0 && fillmap[y-1][x]<n){tx=x; ty=y-1; t=fillmap[y-1][x];}
                    // минимальное значение
                    if(x + 1 < labyrinth.length && fillmap[y][x+1]<n){tx=x+1; ty=y; t=fillmap[y][x+1];}
                    if(x - 1 >= 0 && fillmap[y][x-1]<n){tx=x-1; ty=y; t=fillmap[y][x-1];}
                    if(y + 1 < labyrinth.length && x + 1 < labyrinth.length && fillmap[y+1][x+1]<n){tx=x+1; ty=y+1; t=fillmap[y+1][x+1];}
                    if(y - 1 >= 0 && x - 1 >= 0 && fillmap[y-1][x-1]<n){tx=x-1; ty=y-1; t=fillmap[y-1][x-1];}
                    if(x + 1 < labyrinth.length && y - 1 >= 0 && fillmap[y-1][x+1]<n){tx=x+1; ty=y-1; t=fillmap[y-1][x+1];}
                    if(y + 1 < labyrinth.length && x - 1 >= 0 && fillmap[y+1][x-1]<n){tx=x-1; ty=y+1; t=fillmap[y+1][x-1];}
                    x = tx;
                    y = ty;
                    n = t; // Пеpеходим в найденую клетку
                    path.add(new Point(x,y));
            }
            //инвертируем результат
            Point[] result = new Point[path.size()];
            List<Point> res = new ArrayList<>();
            
            t = path.size();
            for(Object point: path)
                    result[--t] = (Point)point; 
            
            for(Point pt: result)
                res.add((Point)pt);
            return res;
    }
}
