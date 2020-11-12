package drawer;

public class Frame3d extends javax.swing.JFrame {
    
    public Frame3d() {
        initComponents();
    }

    public void update() {
        try{
            Main.update();
        } catch(Exception e) {
            //Hmm...
        }
        keyDetector.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fButton = new javax.swing.JButton();
        bButton = new javax.swing.JButton();
        rButton = new javax.swing.JButton();
        lButton = new javax.swing.JButton();
        uButton = new javax.swing.JButton();
        dButton = new javax.swing.JButton();
        lAButton = new javax.swing.JButton();
        dAButton = new javax.swing.JButton();
        rAButton = new javax.swing.JButton();
        uAButton = new javax.swing.JButton();
        keyDetector = new javax.swing.JTextField();
        lSButton = new javax.swing.JButton();
        rSButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Controller");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                key(evt);
            }
        });

        fButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/x.png"))); // NOI18N
        fButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fButtonActionPerformed(evt);
            }
        });

        bButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/circle.png"))); // NOI18N
        bButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bButtonActionPerformed(evt);
            }
        });

        rButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/arrow3.png"))); // NOI18N
        rButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rButtonActionPerformed(evt);
            }
        });

        lButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/arrow4.png"))); // NOI18N
        lButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lButtonActionPerformed(evt);
            }
        });

        uButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/arrow.png"))); // NOI18N
        uButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uButtonActionPerformed(evt);
            }
        });

        dButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/arrow2.png"))); // NOI18N
        dButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dButtonActionPerformed(evt);
            }
        });

        lAButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/arrow4.png"))); // NOI18N
        lAButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lAButtonActionPerformed(evt);
            }
        });

        dAButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/arrow2.png"))); // NOI18N
        dAButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dAButtonActionPerformed(evt);
            }
        });

        rAButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/arrow3.png"))); // NOI18N
        rAButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rAButtonActionPerformed(evt);
            }
        });

        uAButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/arrow.png"))); // NOI18N
        uAButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uAButtonActionPerformed(evt);
            }
        });

        keyDetector.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        keyDetector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keyDetectorActionPerformed(evt);
            }
        });
        keyDetector.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                key(evt);
            }
        });

        lSButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/arrow4.png"))); // NOI18N
        lSButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lSButtonActionPerformed(evt);
            }
        });

        rSButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/arrow3.png"))); // NOI18N
        rSButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(uButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lAButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lSButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dAButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rAButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(uAButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rSButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(keyDetector))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(uButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uAButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lSButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(bButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(rButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rAButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dAButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lAButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addComponent(keyDetector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fButtonActionPerformed
        Main.addUserZ(1);
        update();
    }//GEN-LAST:event_fButtonActionPerformed

    private void bButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bButtonActionPerformed
        Main.addUserZ(-1);
        update();
    }//GEN-LAST:event_bButtonActionPerformed

    private void uButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uButtonActionPerformed
        Main.addUserY(1);
        update();
    }//GEN-LAST:event_uButtonActionPerformed

    private void dButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dButtonActionPerformed
        Main.addUserY(-1);
        update();
    }//GEN-LAST:event_dButtonActionPerformed

    private void rButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rButtonActionPerformed
        Main.addUserX(1);
        update();
    }//GEN-LAST:event_rButtonActionPerformed

    private void lButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lButtonActionPerformed
        Main.addUserX(-1);
        update();
    }//GEN-LAST:event_lButtonActionPerformed

    private void lAButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lAButtonActionPerformed
        Main.addUserA((double) 1/180*Main.pi);
        update();
    }//GEN-LAST:event_lAButtonActionPerformed

    private void dAButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dAButtonActionPerformed
        Main.addUserB((double) -1/180*Main.pi);
        update();
    }//GEN-LAST:event_dAButtonActionPerformed

    private void rAButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rAButtonActionPerformed
        Main.addUserA((double) -1/180*Main.pi);
        update();
    }//GEN-LAST:event_rAButtonActionPerformed

    private void uAButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uAButtonActionPerformed
        Main.addUserB((double) 1/180*Main.pi);
        update();
    }//GEN-LAST:event_uAButtonActionPerformed

    private void key(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_key
        switch(evt.getKeyChar()) {
            case('w'):
                uButtonActionPerformed(null);
                break;
            case('s'):
                dButtonActionPerformed(null);
                break;
            case('a'):
                lButtonActionPerformed(null);
                break;
            case('d'):
                rButtonActionPerformed(null);
                break;
            case('r'):
                fButtonActionPerformed(null);
                break;
            case('f'):
                bButtonActionPerformed(null);
                break;
            case('i'):
                uAButtonActionPerformed(null);
                break;
            case('k'):
                dAButtonActionPerformed(null);
                break;
            case('j'):
                lAButtonActionPerformed(null);
                break;
            case('l'):
                rAButtonActionPerformed(null);
                break;
            case('u'):
                lSButtonActionPerformed(null);
                break;
            case('o'):
                rSButtonActionPerformed(null);
                break;
        }
        keyDetector.setText("");
    }//GEN-LAST:event_key

    private void keyDetectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keyDetectorActionPerformed
        keyDetector.setText("");
    }//GEN-LAST:event_keyDetectorActionPerformed

    private void lSButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lSButtonActionPerformed
        Main.addUserC((double) -1/180*Main.pi);
        update();
    }//GEN-LAST:event_lSButtonActionPerformed

    private void rSButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonActionPerformed
        Main.addUserC((double) 1/180*Main.pi);
        update();
    }//GEN-LAST:event_rSButtonActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bButton;
    private javax.swing.JButton dAButton;
    private javax.swing.JButton dButton;
    private javax.swing.JButton fButton;
    private javax.swing.JTextField keyDetector;
    private javax.swing.JButton lAButton;
    private javax.swing.JButton lButton;
    private javax.swing.JButton lSButton;
    private javax.swing.JButton rAButton;
    private javax.swing.JButton rButton;
    private javax.swing.JButton rSButton;
    private javax.swing.JButton uAButton;
    private javax.swing.JButton uButton;
    // End of variables declaration//GEN-END:variables
}
