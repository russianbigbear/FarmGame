/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form_pack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import Farm_pack.Map;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MainForm extends javax.swing.JFrame {
    private String _map_name;  // имя карты
    private Map _map; // карта для игры
    private MapDrawer _MapDrawer ; // объект для отрисовки
    private Timer _timer = null; // таймер дискретного моделирования
    private int _id_vegetable;   // id продукта
    private int _vegetable_price; // сколько стоит купить продукт
    private int _cc_click_count;  // количество нажатий чит-кода
            
    /**
     * Creates new form MainForm
     */
    public MainForm() {
             
        initComponents();

        _timer = new Timer (400, (ActionEvent e) -> {  
            _MapDrawer.DrawMap(_map_panel.getGraphics(),_map_panel.getWidth(),_map_panel.getHeight());
            animation_control_panel();
            get_map().live_step();
        });
        
         // получение координат нажатия мыши на _map_panel
        _map_panel.addMouseListener(new MouseAdapter(){            
            @Override
            public void mouseClicked(MouseEvent me){
                if (get_id_vegetable() != -1) {
                    int X = me.getX() / 28;
                    int Y = me.getY() / 28;
                    _map.plant_a_garden_bed(X, Y, get_id_vegetable(), get_vegetable_price());
                    set_id_vegetable(-1);
                    set_vegetable_price(0);
                    click_visible(true);
                    _message_field.setText("");
                }
            }          
        }); 
        
        set_map_name(selection_map());
        set_id_vegetable(-1);
        set_vegetable_price(0);
                  
        this._map = new Map(16, 16, get_map_name()); // карта для игры
        this._MapDrawer = new MapDrawer(_map);
          
        _timer.start();
    }
    /* выбор игрового поля */
    public static String selection_map(){
        String map_name;
        Object[] maps = {"Стандартная карта\n", "Сплошные грядки\n", "Счастье Петровича\n", "Шахматы\n"};
        switch( (String)JOptionPane.showInputDialog(null,"Выберете карту для игры",
                "Выбор карты для игры",JOptionPane.PLAIN_MESSAGE,
                new ImageIcon("src/graphic arts/map.png"),maps, "Стандартная карта\n") ) {
            
            case "Стандартная карта\n": {map_name = "Map_1"; break;}
            case "Сплошные грядки\n": {map_name = "Map_2"; break;}
            case "Счастье Петровича\n": {map_name = "Map_3"; break;}
            case "Шахматы\n": {map_name = "Map_4"; break;}
            default: {map_name = "Map_1"; break;}
        }

        return map_name;
    }
    /* анимация панели управления */
    private void animation_control_panel(){
        this._add_spray.setIcon(new ImageIcon("src/graphic arts/spray_1.png"));
        this._extermination.setIcon(new ImageIcon("src/graphic arts/extermination.png"));
        this._harvest.setIcon(new ImageIcon("src/graphic arts/harvest.png"));
        
        this._money_count_text.setText(String.valueOf(get_map().get_money()));
        this._day_count_text.setText(String.valueOf(get_map().get_day_count()));
        this._spray_count_text.setText(String.valueOf(get_map().get_spray_count()));  
    }
    
    /*метод установки видимости обектов на панели*/
    private void click_visible(boolean visible){
        this._Toolbar_title.setVisible(visible);
        this._carrot_label.setVisible(visible);
        this._add_carrot.setVisible(visible);
        this._cucumber_label.setVisible(visible);
        this._add_cucumber.setVisible(visible);
        this._eggplant_label.setVisible(visible);
        this._add_eggplant.setVisible(visible);
        this._potato_label.setVisible(visible);
        this._add_potato.setVisible(visible);
        this._tomato_label.setVisible(visible);
        this._add_tomato.setVisible(visible); 
        this._add_spray_label.setVisible(visible);
        this._add_spray.setVisible(visible);
        this._extermination.setVisible(visible);
        this._extermination_label.setVisible(visible);
        this._harvest.setVisible(visible);
        this._harvest_label.setVisible(visible);
    }
    
    /*вывод сообщения*/
    private void no_money_message(){
        JOptionPane.showMessageDialog(this,"Упс, денег нет!", "Петрович", JOptionPane.CLOSED_OPTION);
    }
    
    /*установка имени карты*/
    public void set_map_name(String map_name){
        this._map_name = map_name;
    }
    
    /*установка id овоща*/
    public void set_id_vegetable(int id_vegetable){
        this._id_vegetable = id_vegetable;
    }
    
    /*установка количества нажатий на _cheat_code*/
    public void set_cc_click_count(int cc_click_count){
        this._cc_click_count = cc_click_count;
    }
    
    /*возврат имени карты */
    public String get_map_name(){
        return this._map_name ;
    }
    
    /*возврат карты, на которой рисуем*/
    public Map get_map(){
        return this._map;
    }
    
    /*возврат id овоща*/
    public int get_id_vegetable(){
        return this._id_vegetable;
    }
    
    /* установка флага посадки*/
    public void set_vegetable_price(int vegetable_price){
        this._vegetable_price = vegetable_price;
    }
    
    /*возврат флага посадки*/
    public int get_vegetable_price(){
        return this._vegetable_price;
    }
    
    /*возврат количества нажатий на _cheat_code*/
    public int get_cc_click_count(){
        return this._cc_click_count;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        _map_panel = new javax.swing.JPanel();
        _control_panel = new javax.swing.JPanel();
        _money_title = new javax.swing.JLabel();
        _money_label = new javax.swing.JLabel();
        _money_count_text = new javax.swing.JLabel();
        _day_title = new javax.swing.JLabel();
        _day_label = new javax.swing.JLabel();
        _day_count_text = new javax.swing.JLabel();
        _Control_title = new javax.swing.JLabel();
        _spray_title = new javax.swing.JLabel();
        _spray_label = new javax.swing.JLabel();
        _spray_count_text = new javax.swing.JLabel();
        _add_garden_bed_panel = new javax.swing.JPanel();
        _add_carrot = new javax.swing.JButton();
        _add_cucumber = new javax.swing.JButton();
        _add_eggplant = new javax.swing.JButton();
        _add_potato = new javax.swing.JButton();
        _add_tomato = new javax.swing.JButton();
        _add_spray = new javax.swing.JButton();
        _extermination = new javax.swing.JButton();
        _carrot_label = new javax.swing.JLabel();
        _cucumber_label = new javax.swing.JLabel();
        _eggplant_label = new javax.swing.JLabel();
        _potato_label = new javax.swing.JLabel();
        _tomato_label = new javax.swing.JLabel();
        _add_spray_label = new javax.swing.JLabel();
        _extermination_label = new javax.swing.JLabel();
        _harvest_label = new javax.swing.JLabel();
        _harvest = new javax.swing.JButton();
        _Toolbar_title = new javax.swing.JLabel();
        _cheat_code = new javax.swing.JButton();
        _message_field = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Маленькая ферма");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(896, 750));

        _map_panel.setBackground(new java.awt.Color(204, 204, 204));
        _map_panel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        _map_panel.setFocusable(false);
        _map_panel.setMaximumSize(new java.awt.Dimension(448, 448));
        _map_panel.setPreferredSize(new java.awt.Dimension(448, 448));

        javax.swing.GroupLayout _map_panelLayout = new javax.swing.GroupLayout(_map_panel);
        _map_panel.setLayout(_map_panelLayout);
        _map_panelLayout.setHorizontalGroup(
            _map_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 448, Short.MAX_VALUE)
        );
        _map_panelLayout.setVerticalGroup(
            _map_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 448, Short.MAX_VALUE)
        );

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${graphics}"), _control_panel, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        _money_title.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        _money_title.setText("Капитал");

        _money_label.setBackground(new java.awt.Color(204, 204, 204));
        _money_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphic arts/money.png"))); // NOI18N

        _day_title.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        _day_title.setText("Дни");

        _day_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphic arts/time.png"))); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${_day_count}"), _day_count_text, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        _Control_title.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        _Control_title.setText("Ваши показатели");

        _spray_title.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        _spray_title.setText("Отрава");

        _spray_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphic arts/spray_1.png"))); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${graphics}"), _add_garden_bed_panel, org.jdesktop.beansbinding.BeanProperty.create("background"));
        bindingGroup.addBinding(binding);

        _add_carrot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphic arts/carrot_label.png"))); // NOI18N
        _add_carrot.setBorder(null);
        _add_carrot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                _add_carrotMouseClicked(evt);
            }
        });

        _add_cucumber.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphic arts/cucumber_label.png"))); // NOI18N
        _add_cucumber.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                _add_cucumberMouseClicked(evt);
            }
        });

        _add_eggplant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphic arts/eggplant_label.png"))); // NOI18N
        _add_eggplant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                _add_eggplantMouseClicked(evt);
            }
        });

        _add_potato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphic arts/potato_label.png"))); // NOI18N
        _add_potato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                _add_potatoMouseClicked(evt);
            }
        });

        _add_tomato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphic arts/tomato_label.png"))); // NOI18N
        _add_tomato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                _add_tomatoMouseClicked(evt);
            }
        });

        _add_spray.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                _add_sprayMouseClicked(evt);
            }
        });

        _extermination.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                _exterminationMouseClicked(evt);
            }
        });

        _carrot_label.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        _carrot_label.setText("<html>\nМорковка\n<br>\n 150 Руб\n<html>");

        _cucumber_label.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        _cucumber_label.setText("<html>\nОгурцы\n<br>\n 175 Руб\n<html>");

        _eggplant_label.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        _eggplant_label.setText("<html>\nБаклажаны\n<br>\n&nbsp 125 Руб\n<html>");

        _potato_label.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        _potato_label.setText("<html>\nКартошка\n<br>\n&nbsp 100 Руб\n<html>");

        _tomato_label.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        _tomato_label.setText("<html>\nПомидоры \n<br>\n&nbsp 150 Руб\n<html>");

        _add_spray_label.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        _add_spray_label.setText("<html>\nОтрава\n<br>\n 500 Руб\n<html>");

        _extermination_label.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        _extermination_label.setText("<html>\nДезинсекция\n<br>\n&nbsp 3000 Руб\n<html>");

        _harvest_label.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        _harvest_label.setText("<html>\nУборка урожая\n<br>\n&nbsp &nbsp 2000 Руб\n<html>");

        _harvest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                _harvestMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout _add_garden_bed_panelLayout = new javax.swing.GroupLayout(_add_garden_bed_panel);
        _add_garden_bed_panel.setLayout(_add_garden_bed_panelLayout);
        _add_garden_bed_panelLayout.setHorizontalGroup(
            _add_garden_bed_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(_add_garden_bed_panelLayout.createSequentialGroup()
                .addGroup(_add_garden_bed_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(_add_garden_bed_panelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(_add_garden_bed_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(_carrot_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(_add_garden_bed_panelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(_add_carrot, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(_add_garden_bed_panelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(_add_garden_bed_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(_add_garden_bed_panelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(_add_spray, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(_add_spray_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(_add_garden_bed_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(_add_garden_bed_panelLayout.createSequentialGroup()
                        .addGroup(_add_garden_bed_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(_cucumber_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(_add_garden_bed_panelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(_add_cucumber, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5)
                        .addGroup(_add_garden_bed_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(_add_garden_bed_panelLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(_add_eggplant, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(_add_garden_bed_panelLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(_eggplant_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(_add_garden_bed_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(_add_garden_bed_panelLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(_potato_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(_add_garden_bed_panelLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(_add_potato, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(_add_garden_bed_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(_add_garden_bed_panelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(_tomato_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(_add_garden_bed_panelLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(_add_tomato, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(_add_garden_bed_panelLayout.createSequentialGroup()
                        .addGroup(_add_garden_bed_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(_extermination_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(_add_garden_bed_panelLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(_extermination, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(_add_garden_bed_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(_add_garden_bed_panelLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(_harvest_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(_add_garden_bed_panelLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(_harvest, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(38, 38, 38))
        );
        _add_garden_bed_panelLayout.setVerticalGroup(
            _add_garden_bed_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(_add_garden_bed_panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(_add_garden_bed_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(_carrot_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(_cucumber_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(_eggplant_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(_potato_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(_tomato_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(_add_garden_bed_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(_add_cucumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(_add_carrot, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(_add_eggplant, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(_add_potato, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(_add_tomato, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(_add_garden_bed_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(_add_garden_bed_panelLayout.createSequentialGroup()
                        .addComponent(_add_spray_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_add_spray, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(_add_garden_bed_panelLayout.createSequentialGroup()
                        .addComponent(_extermination_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_extermination, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(_add_garden_bed_panelLayout.createSequentialGroup()
                        .addComponent(_harvest_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_harvest, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        _Toolbar_title.setFont(new java.awt.Font("Segoe Script", 1, 14)); // NOI18N
        _Toolbar_title.setText("Магазин бабы Зины");

        _cheat_code.setBackground(Color.decode("#cfb08d"));
        _cheat_code.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphic arts/cc_icon.png"))); // NOI18N
        _cheat_code.setBorder(null);
        _cheat_code.setBorderPainted(false);
        _cheat_code.setDefaultCapable(false);
        _cheat_code.setFocusPainted(false);
        _cheat_code.setMargin(new java.awt.Insets(0, 0, 0, 0));
        _cheat_code.setRequestFocusEnabled(false);
        _cheat_code.setRolloverEnabled(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${graphics}"), _cheat_code, org.jdesktop.beansbinding.BeanProperty.create("foreground"));
        bindingGroup.addBinding(binding);

        _cheat_code.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                _cheat_codeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout _control_panelLayout = new javax.swing.GroupLayout(_control_panel);
        _control_panel.setLayout(_control_panelLayout);
        _control_panelLayout.setHorizontalGroup(
            _control_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(_control_panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(_control_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(_control_panelLayout.createSequentialGroup()
                        .addGroup(_control_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(_control_panelLayout.createSequentialGroup()
                                .addComponent(_cheat_code, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(_money_title))
                            .addGroup(_control_panelLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(_money_label, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(_money_count_text, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(_day_label, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)
                        .addGroup(_control_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(_control_panelLayout.createSequentialGroup()
                                .addComponent(_day_count_text, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(_spray_label, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(_spray_count_text, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(_control_panelLayout.createSequentialGroup()
                                .addComponent(_day_title)
                                .addGap(93, 93, 93)
                                .addComponent(_spray_title))))
                    .addComponent(_add_garden_bed_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(_control_panelLayout.createSequentialGroup()
                .addGroup(_control_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(_control_panelLayout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(_Toolbar_title))
                    .addGroup(_control_panelLayout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(_Control_title)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        _control_panelLayout.setVerticalGroup(
            _control_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(_control_panelLayout.createSequentialGroup()
                .addComponent(_Control_title)
                .addGap(0, 0, 0)
                .addGroup(_control_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(_day_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, _control_panelLayout.createSequentialGroup()
                        .addGroup(_control_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(_control_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(_day_title, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(_control_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(_money_title)
                                    .addComponent(_spray_title)))
                            .addComponent(_cheat_code, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(_control_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(_control_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(_control_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(_money_label, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(_money_count_text, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(_day_count_text, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(_control_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(_spray_label, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(_spray_count_text, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(10, 10, 10)
                .addComponent(_Toolbar_title)
                .addGap(0, 0, 0)
                .addComponent(_add_garden_bed_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        _message_field.setEditable(false);
        _message_field.setBackground(Color.decode("#cfb08d"));
        _message_field.setFont(new java.awt.Font("Segoe Print", 0, 12)); // NOI18N
        _message_field.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        _message_field.setToolTipText("");
        _message_field.setAutoscrolls(false);
        _message_field.setBorder(null);
        _message_field.setCaretColor(Color.decode("#cfb08d"));
        _message_field.setCursor(null);
        _message_field.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        _message_field.setFocusable(false);
        _message_field.setRequestFocusEnabled(false);
        _message_field.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(_map_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(_control_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(_message_field, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(_map_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(_control_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(_message_field, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("");

        bindingGroup.bind();

        setSize(new java.awt.Dimension(902, 477));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /*покупка томатов*/
    private void _add_tomatoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event__add_tomatoMouseClicked
        if(get_map().get_money() >= 150){
            click_visible(false);
            set_id_vegetable(5);
            set_vegetable_price(150);
            this._message_field.setText("Нажмите на грядку, чтобы посадить помидоры.");
        }
        else
            no_money_message(); 
    }//GEN-LAST:event__add_tomatoMouseClicked

    /*покупка картошки*/
    private void _add_potatoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event__add_potatoMouseClicked
        if(get_map().get_money() >= 100){
            click_visible(false);
            set_id_vegetable(4);
            set_vegetable_price(100);
            this._message_field.setText("Нажмите на грядку, чтобы посадить картошку.");
 
        }
        else
            no_money_message();
    }//GEN-LAST:event__add_potatoMouseClicked

    /*покупка баклажанов*/
    private void _add_eggplantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event__add_eggplantMouseClicked
        if(get_map().get_money() >= 125){
            click_visible(false);
            set_id_vegetable(3);
            set_vegetable_price(125); 
            this._message_field.setText("Нажмите на грядку, чтобы посадить баклажаны.");
        }
        else
            no_money_message();
    }//GEN-LAST:event__add_eggplantMouseClicked

    /*покупка огурцов*/
    private void _add_cucumberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event__add_cucumberMouseClicked
        if(get_map().get_money() >= 175){ 
            click_visible(false);
            set_id_vegetable(2);
            set_vegetable_price(175);
            this._message_field.setText("Нажмите на грядку, чтобы посадить огурцы.");
        }
        else
            no_money_message();
    }//GEN-LAST:event__add_cucumberMouseClicked

    /*покупка морковки*/
    private void _add_carrotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event__add_carrotMouseClicked
        if(get_map().get_money() >= 150){
            click_visible(false);
            set_id_vegetable(1); 
            set_vegetable_price(150);
            this._message_field.setText("Нажмите на грядку, чтобы посадить морковку.");
        }
        else
            no_money_message();
    }//GEN-LAST:event__add_carrotMouseClicked

    /*покупка отравы*/
    private void _add_sprayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event__add_sprayMouseClicked
        this._add_spray.setIcon(new ImageIcon("src/graphic arts/spray_2.png"));
        if(get_map().get_money() >= 500){
            get_map().set_spray_count(get_map().get_spray_count() + 1);
            get_map().set_money(get_map().get_money() - 500);
        }
        else
            no_money_message();

    }//GEN-LAST:event__add_sprayMouseClicked

    /*чит код консоль*/
    private void _cheat_codeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event__cheat_codeMouseClicked
        if(get_cc_click_count() == 6){
            switch(JOptionPane.showInputDialog(null, "Введите команду:", "Cheat code console", JOptionPane.ERROR_MESSAGE)){
                case "/add_money": {get_map().set_money(get_map().get_money() + 10000); break;}
                case "/add_spray": {get_map().set_spray_count(get_map().get_spray_count() + 10); break;}
                case "/add_day": {get_map().set_day_count(get_map().get_day_count() + 1); break;}
                default: break;
            }

            set_cc_click_count(0);
        }
        else
            set_cc_click_count(get_cc_click_count() + 1);
    }//GEN-LAST:event__cheat_codeMouseClicked

    /*зачистка от паразитов и пропавших грядок*/
    private void _exterminationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event__exterminationMouseClicked
        this._extermination.setIcon(new ImageIcon("src/graphic arts/extermination_done.png"));
        if(get_map().get_money() >= 3000){
            get_map().extermination();
            get_map().set_money(get_map().get_money() - 3000);
        }
        else
            no_money_message();
    }//GEN-LAST:event__exterminationMouseClicked

    /*сбор всего урожая*/
    private void _harvestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event__harvestMouseClicked
        this._harvest.setIcon(new ImageIcon("src/graphic arts/harvest_done.png"));
        if(get_map().get_money() >= 2000){
            get_map().harvest();
            get_map().set_money(get_map().get_money() - 2000);
        }
        else
            no_money_message();
    }//GEN-LAST:event__harvestMouseClicked

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            MainForm MF = new MainForm();
            MF.getContentPane().setBackground(Color.decode("#cfb08d"));
            MF.setResizable(false);
            MF.setVisible(true);
      
        });
    }

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel _Control_title;
    private javax.swing.JLabel _Toolbar_title;
    private javax.swing.JButton _add_carrot;
    private javax.swing.JButton _add_cucumber;
    private javax.swing.JButton _add_eggplant;
    private javax.swing.JPanel _add_garden_bed_panel;
    private javax.swing.JButton _add_potato;
    private javax.swing.JButton _add_spray;
    private javax.swing.JLabel _add_spray_label;
    private javax.swing.JButton _add_tomato;
    private javax.swing.JLabel _carrot_label;
    private javax.swing.JButton _cheat_code;
    private javax.swing.JPanel _control_panel;
    private javax.swing.JLabel _cucumber_label;
    private javax.swing.JLabel _day_count_text;
    private javax.swing.JLabel _day_label;
    private javax.swing.JLabel _day_title;
    private javax.swing.JLabel _eggplant_label;
    private javax.swing.JButton _extermination;
    private javax.swing.JLabel _extermination_label;
    private javax.swing.JButton _harvest;
    private javax.swing.JLabel _harvest_label;
    private javax.swing.JPanel _map_panel;
    private javax.swing.JTextField _message_field;
    private javax.swing.JLabel _money_count_text;
    private javax.swing.JLabel _money_label;
    private javax.swing.JLabel _money_title;
    private javax.swing.JLabel _potato_label;
    private javax.swing.JLabel _spray_count_text;
    private javax.swing.JLabel _spray_label;
    private javax.swing.JLabel _spray_title;
    private javax.swing.JLabel _tomato_label;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
