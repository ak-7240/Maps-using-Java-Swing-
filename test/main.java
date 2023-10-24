/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import data.routingData;
import data.routingService;
import functions.calculateDistance;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Double.parseDouble;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;
import waypoint.EventWayPoint;
import waypoint.MyWayPoint;
import waypoint.WayPointRender;

/**
 *
 * @author Anuradha
 */
public class main extends javax.swing.JFrame {
     
   
     private final Set<MyWayPoint> waypoints = new HashSet<>();
    private List<routingData> routingData = new ArrayList<>();
    private EventWayPoint event;
    private Point mousePosition;
    double lon1 = 0,lon2 =0, lat1 =0, lat2=0;
    
    String description;
    String unit = "K"; 

    
    /**
     * Creates new form main
     */
    public main() {
        initComponents();
        init();
    }
    
    private void init() {
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jxMapViewer.setTileFactory(tileFactory);
        GeoPosition geo = new GeoPosition(13.0827, 80.2707);
        jxMapViewer.setAddressLocation(geo);
        jxMapViewer.setZoom(12);
        disValLabel.setText(String.valueOf(calculateDistance.distance(lat1,lon1,lat2,lon2,unit)));
        disLabel.setBackground(new java.awt.Color(240, 240, 240,150));
        

        
         MouseInputListener mm = new PanMouseInputListener(jxMapViewer);
        jxMapViewer.addMouseListener(mm);
        jxMapViewer.addMouseMotionListener(mm);
        jxMapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(jxMapViewer));
        event = getEvent();
        
        waypointPanel.setVisible(false);
         waypointPanel.setBackground(new java.awt.Color(240, 240, 240,255));
         jPanel1.setBackground(new java.awt.Color(250, 250, 250));
      
    }
    
     private void addWayPoint(MyWayPoint waypoint) {
          for (MyWayPoint d : waypoints) {
            jxMapViewer.remove(d.getButton());
        }
        Iterator<MyWayPoint> iter = waypoints.iterator();
        while (iter.hasNext()) {
            if (iter.next().getPointType() == waypoint.getPointType()) {
                iter.remove();
            }
        }
        waypoints.add(waypoint);
        initWayPoint();
    }
    
    private void initWayPoint() {
        WaypointPainter<MyWayPoint> wp = new WayPointRender();
        wp.setWaypoints(waypoints);
        jxMapViewer.setOverlayPainter(wp);
        for (MyWayPoint d : waypoints) {
            jxMapViewer.add(d.getButton());
        }
        //  Routing Data
        if (waypoints.size() == 2) {
            GeoPosition start = null;
            GeoPosition end = null;
            for (MyWayPoint w : waypoints) {
                if (w.getPointType() == MyWayPoint.PointType.START) {
                    start = w.getPosition();
                } else if (w.getPointType() == MyWayPoint.PointType.END) {
                    end = w.getPosition();
                }
            }
            if (start != null && end != null) {
                routingData = routingService.getInstance().routing(start.getLatitude(), start.getLongitude(), end.getLatitude(), end.getLongitude());

            } else {
                routingData.clear();
            }
            jxMapViewer.setRoutingData(routingData);
        }
    }

    private void clearWayPoint() {
        for (MyWayPoint d : waypoints) {
            jxMapViewer.remove(d.getButton());
        }
        waypoints.clear();
        initWayPoint();
    }
    
    private EventWayPoint getEvent() {
        return new EventWayPoint() {
            @Override
            public void selected(MyWayPoint waypoint) {
                JOptionPane.showMessageDialog(main.this, waypoint.getName());
            }
        };
    }
    
    private void addWaypoint(MyWayPoint waypoint) {
        for (MyWayPoint d : waypoints) {
            jxMapViewer.remove(d.getButton());
        }
        waypoints.add(waypoint);
        initWaypoint();
    }

    private void initWaypoint() {
        WaypointPainter<MyWayPoint> wp = new WayPointRender();
        wp.setWaypoints(waypoints);
        jxMapViewer.setOverlayPainter(wp);
        for (MyWayPoint d : waypoints) {
            jxMapViewer.add(d.getButton());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        menuStart = new javax.swing.JMenuItem();
        menuEnd = new javax.swing.JMenuItem();
        jxMapViewer = new data.JXMapViewerCustom();
        jPanel1 = new javax.swing.JPanel();
        addWayPoint = new javax.swing.JButton();
        clearWayPoint = new javax.swing.JButton();
        comboMapType = new javax.swing.JComboBox<>();
        disLabel = new javax.swing.JLabel();
        disValLabel = new javax.swing.JLabel();
        unitType = new javax.swing.JComboBox<>();
        waypointPanel = new javax.swing.JPanel();
        latt1 = new javax.swing.JTextField();
        goButton = new javax.swing.JButton();
        long1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        desLabel = new javax.swing.JTextArea();

        menuStart.setText("Start");
        menuStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuStartActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menuStart);

        menuEnd.setText("End");
        menuEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEndActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menuEnd);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jxMapViewer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jxMapViewerMouseReleased(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        addWayPoint.setText("Add Point");
        addWayPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addWayPointActionPerformed(evt);
            }
        });

        clearWayPoint.setText("Clear Points");
        clearWayPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearWayPointActionPerformed(evt);
            }
        });

        comboMapType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Open Street ", "Virtual Earth", "Hybrid", "Satellite", " ", " " }));
        comboMapType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMapTypeActionPerformed(evt);
            }
        });

        disLabel.setBackground(new java.awt.Color(255, 255, 255));
        disLabel.setText("Distance");
        disLabel.setOpaque(true);

        disValLabel.setBackground(new java.awt.Color(255, 255, 255));
        disValLabel.setText("jLabel2");
        disValLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        disValLabel.setOpaque(true);

        unitType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Miles", "Km", "Nautical Miles", " " }));
        unitType.setSelectedIndex(1);
        unitType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitTypeActionPerformed(evt);
            }
        });

        waypointPanel.setBackground(new java.awt.Color(255, 255, 255));

        latt1.setText("Enter Latitude");
        latt1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                latt1FocusLost(evt);
            }
        });
        latt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                latt1MouseClicked(evt);
            }
        });
        latt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                latt1ActionPerformed(evt);
            }
        });

        goButton.setText("MARK POINT");
        goButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goButtonActionPerformed(evt);
            }
        });

        long1.setText("Enter Longitude");
        long1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                long1FocusLost(evt);
            }
        });
        long1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                long1MouseClicked(evt);
            }
        });
        long1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                long1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Longitude");

        jLabel4.setText("Description");

        jLabel5.setText("Latitude");

        desLabel.setColumns(20);
        desLabel.setRows(5);
        desLabel.setText("Enter Place description");
        desLabel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                desLabelFocusLost(evt);
            }
        });
        desLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                desLabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(desLabel);

        javax.swing.GroupLayout waypointPanelLayout = new javax.swing.GroupLayout(waypointPanel);
        waypointPanel.setLayout(waypointPanelLayout);
        waypointPanelLayout.setHorizontalGroup(
            waypointPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(waypointPanelLayout.createSequentialGroup()
                .addGroup(waypointPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(waypointPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(waypointPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(waypointPanelLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(goButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, waypointPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(waypointPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(latt1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(long1)
                    .addGroup(waypointPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)))
                .addGap(238, 238, 238))
        );
        waypointPanelLayout.setVerticalGroup(
            waypointPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(waypointPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(latt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(long1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(goButton)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clearWayPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboMapType, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addWayPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(waypointPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(disLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(disValLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addComponent(unitType, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addWayPoint)
                .addGap(34, 34, 34)
                .addComponent(clearWayPoint)
                .addGap(36, 36, 36)
                .addComponent(comboMapType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(disValLabel)
                    .addComponent(unitType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(disLabel))
                .addGap(18, 18, 18)
                .addComponent(waypointPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jxMapViewerLayout = new javax.swing.GroupLayout(jxMapViewer);
        jxMapViewer.setLayout(jxMapViewerLayout);
        jxMapViewerLayout.setHorizontalGroup(
            jxMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jxMapViewerLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 777, Short.MAX_VALUE))
        );
        jxMapViewerLayout.setVerticalGroup(
            jxMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jxMapViewer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jxMapViewer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void comboMapTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMapTypeActionPerformed
        // TODO add your handling code here:
        
        TileFactoryInfo info;
        int index = comboMapType.getSelectedIndex();
        if (index == 0) {
            info = new OSMTileFactoryInfo();
        } else if (index == 1) {
            info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
        } else if (index == 2) {
            info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.HYBRID);
        } else {
            info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.SATELLITE);
        }
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jxMapViewer.setTileFactory(tileFactory);
    }//GEN-LAST:event_comboMapTypeActionPerformed

    private void addWayPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addWayPointActionPerformed
        waypointPanel.setVisible(true);
        desLabel.setText("Enter Place Description");
        latt1.setText("Enter Latitude");
        long1.setText("Enter Longitude");
    }//GEN-LAST:event_addWayPointActionPerformed

    private void clearWayPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearWayPointActionPerformed
        // TODO add your handling code here:
         clearWayPoint();
    }//GEN-LAST:event_clearWayPointActionPerformed

    private void menuStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStartActionPerformed
        // TODO add your handling code here:
           GeoPosition geop = jxMapViewer.convertPointToGeoPosition(mousePosition);
        MyWayPoint wayPoint = new MyWayPoint("Start Location", MyWayPoint.PointType.START, event, new GeoPosition(geop.getLatitude(), geop.getLongitude()));
        addWayPoint(wayPoint);
       System.out.println(geop.getLatitude() + " " + geop.getLongitude()+"     Start");
       lat1 = geop.getLatitude();
       lon1 = geop.getLongitude();
       System.out.println(lat1 + lat2 + lon1 +lon2);
       //System.out.println(calculateDistance(lat1,lon1,lat2,lon2));
       disValLabel.setText(String.valueOf(calculateDistance.distance(lat1,lon1,lat2,lon2,unit)));
    }//GEN-LAST:event_menuStartActionPerformed

    private void menuEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEndActionPerformed
        // TODO add your handling code here:
      
        GeoPosition geop = jxMapViewer.convertPointToGeoPosition(mousePosition);
        MyWayPoint wayPoint = new MyWayPoint("End Location", MyWayPoint.PointType.END, event, new GeoPosition(geop.getLatitude(), geop.getLongitude()));
        addWayPoint(wayPoint);
        System.out.println(geop.getLatitude() + " " + geop.getLongitude()+"     End");
        lat2 = geop.getLatitude();
        lon2 = geop.getLongitude();
        System.out.println(lat1 + lat2 + lon1 +lon2);
        //System.out.println(calculateDistance(lat1,lon1,lat2,lon2));
        //disValLabel.setText(String.valueOf(calculateDistance(lat1,lon1,lat2,lon2)));
        disValLabel.setText(String.valueOf(calculateDistance.distance(lat1,lon1,lat2,lon2,unit)));
    }//GEN-LAST:event_menuEndActionPerformed

    private void jxMapViewerMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jxMapViewerMouseReleased
        // TODO add your handling code here:
         if (SwingUtilities.isRightMouseButton(evt)) {
            mousePosition = evt.getPoint();
            jPopupMenu1.show(jxMapViewer, evt.getX(), evt.getY());
            
        }
    }//GEN-LAST:event_jxMapViewerMouseReleased

    private void unitTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitTypeActionPerformed
        // TODO add your handling code here:
         int index = unitType.getSelectedIndex();
        if (index == 0) {
            unit = "M";
                   disValLabel.setText(String.valueOf(calculateDistance.distance(lat1,lon1,lat2,lon2,unit)));
                    
        } else if (index == 1) {
            unit = "K";
                   disValLabel.setText(String.valueOf(calculateDistance.distance(lat1,lon1,lat2,lon2,unit)));

        } else if (index == 2) {
            unit = "N";
                   disValLabel.setText(String.valueOf(calculateDistance.distance(lat1,lon1,lat2,lon2,unit)));

        }
    }//GEN-LAST:event_unitTypeActionPerformed

    private void goButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goButtonActionPerformed
        // TODO add your handling code here:
        if(desLabel.getText().equals("Enter Place Description")){
            description = " ";
        } 
        else{
        description = desLabel.getText();
        }
          addWaypoint(new MyWayPoint(description,MyWayPoint.PointType.POINT, event, new GeoPosition(lat1, lon1)));
       
        
        try {
  //a = Double.parseDouble(b);
        lat1 = parseDouble(latt1.getText());
        lon1 = parseDouble(long1.getText());
         if (lat1 >90 || lat1 <-90 || lon1 >180 || lon1 <-180)
       {
         
         throw new NumberFormatException("Integer is out of rangeeeeeeeeeeeee."); 
       }
         waypointPanel.setVisible(false);
} catch (NumberFormatException e) {
  //the parseDouble failed and you need to handle it here
  System.out.println("EROOR ");
  JOptionPane.showMessageDialog(main.this, " Please enter proper values");
  waypointPanel.setVisible(true);
}
       
        
        //startPoint();
        
    }//GEN-LAST:event_goButtonActionPerformed

    private void latt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_latt1ActionPerformed
        // TODO add your handling code here:
        
       
    }//GEN-LAST:event_latt1ActionPerformed

    private void long1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_long1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_long1ActionPerformed

    private void latt1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_latt1MouseClicked
        // TODO add your handling code here:
        if(latt1.getText().equals("Enter Latitude")){
        latt1.setText("");
        }
         
    }//GEN-LAST:event_latt1MouseClicked

    private void long1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_long1MouseClicked
        // TODO add your handling code here:
        if(long1.getText().equals("Enter Longitude")){
        long1.setText("");
        }
    }//GEN-LAST:event_long1MouseClicked

    private void desLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desLabelMouseClicked
        // TODO add your handling code here:
        if(desLabel.getText().equals("Enter Place Description")){
        desLabel.setText("");
        }
       
    }//GEN-LAST:event_desLabelMouseClicked

    private void latt1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_latt1FocusLost
        // TODO add your handling code here:
        if(latt1.getText().equals("")){
            latt1.setText("Enter Latitude");
        }
       
        
        
    }//GEN-LAST:event_latt1FocusLost

    private void long1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_long1FocusLost
        // TODO add your handling code here:
         if(long1.getText().equals("")){
            long1.setText("Enter Longitude");
        }
    }//GEN-LAST:event_long1FocusLost

    private void desLabelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_desLabelFocusLost
        // TODO add your handling code here:
        if(desLabel.getText().equals("")){
            desLabel.setText("Enter Place Description");
        }
    }//GEN-LAST:event_desLabelFocusLost

    
  
    

    
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addWayPoint;
    private javax.swing.JButton clearWayPoint;
    private javax.swing.JComboBox<String> comboMapType;
    private javax.swing.JTextArea desLabel;
    private javax.swing.JLabel disLabel;
    private javax.swing.JLabel disValLabel;
    private javax.swing.JButton goButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private data.JXMapViewerCustom jxMapViewer;
    private javax.swing.JTextField latt1;
    private javax.swing.JTextField long1;
    private javax.swing.JMenuItem menuEnd;
    private javax.swing.JMenuItem menuStart;
    private javax.swing.JComboBox<String> unitType;
    private javax.swing.JPanel waypointPanel;
    // End of variables declaration//GEN-END:variables

  
   
   
 
    
}
