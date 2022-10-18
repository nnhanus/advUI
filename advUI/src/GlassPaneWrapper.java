
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class GlassPaneWrapper extends JPanel {

    PlayingPanel wrappedPanel;
    SpringLayout layout = new SpringLayout();
    public JSpinner forCount;

    public GlassPaneWrapper(PlayingPanel givenPanel) {
        this.wrappedPanel=givenPanel;
        this.setLayout(layout);
        this.setOpaque(false);
        this.setVisible(false);
        forCount = new JSpinner();
        forCount.setModel(new SpinnerNumberModel(2,2,4,1));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                Point point = e.getPoint();
                if(wrappedPanel.getBottomPanel().view.play.contains(SwingUtilities.convertPoint(e.getComponent(),point,wrappedPanel.bottomPanel.view.play))){
                    wrappedPanel.getBottomPanel().view.play.dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.bottomPanel.view.play));
                }
                else if(wrappedPanel.getBottomPanel().view.clear.contains(SwingUtilities.convertPoint(e.getComponent(),point,wrappedPanel.bottomPanel.view.clear))){
                    wrappedPanel.getBottomPanel().view.clear.dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.bottomPanel.view.clear));}
                else if(wrappedPanel.getBottomPanel().view.redo.contains(SwingUtilities.convertPoint(e.getComponent(),point,wrappedPanel.bottomPanel.view.redo))){
                    wrappedPanel.getBottomPanel().view.redo.dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.bottomPanel.view.redo));}
                else if(wrappedPanel.getBottomPanel().contains(SwingUtilities.convertPoint(e.getComponent(),point,wrappedPanel.getBottomPanel()))){
                    wrappedPanel.getBottomPanel().dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.getBottomPanel()));
                } else if (wrappedPanel.getTopPanel().contains(SwingUtilities.convertPoint(e.getComponent(),point,wrappedPanel.topPanel))){
                    wrappedPanel.getTopPanel().dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.getTopPanel()));
                }

            }
            @Override
            public void mousePressed(MouseEvent e) {
                wrappedPanel.getBottomPanel().dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.getBottomPanel()));
                wrappedPanel.getTopPanel().dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.getTopPanel()));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                wrappedPanel.getBottomPanel().dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.getBottomPanel()));
                wrappedPanel.getTopPanel().dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.getTopPanel()));

            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}

        });
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //get blocks played from index that corresponds with location of cell clicked, on release add block to cell location and
                //delete cell from previous index in blocks played and action list
                //wrappedPanel.getBottomPanel().mouseEvent=true;
                Point mouse = SwingUtilities.convertPoint(e.getComponent(),e.getPoint(),wrappedPanel.getBottomPanel());
                for (cellRectangle cell : wrappedPanel.getBottomPanel().getCells()) {
                    if(cell.contains(mouse)) {
                        cell.setHighlight(true);
                        wrappedPanel.getBottomPanel().repaint();
                    }else{
                        cell.setHighlight(false);
                        wrappedPanel.getBottomPanel().repaint();
                    }
                }
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                wrappedPanel.getBottomPanel().dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.getBottomPanel()));
                wrappedPanel.getTopPanel().dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.getTopPanel()));
            }


        });
        this.setFocusable(true);

    }



    public void activateGlassPane(boolean activate) {
        this.setVisible(activate);
        if (activate) {
            this.requestFocusInWindow();
            this.setFocusTraversalKeysEnabled(false);
        }
    }




    public void addNumberSpinner() {
        BlockControl forBlock=wrappedPanel.topPanel.getButtonList().get(2);
        Point blockPos =SwingUtilities.convertPoint(wrappedPanel.topPanel.blockPanel,forBlock.getLocation(),this);
        this.add(forCount);
        if (blockPos.x < 850 && GameWindow.getLevelNumber()==3) {
            blockPos = new Point(854, 20);
        } else if (blockPos.x < 810 && (GameWindow.getLevelNumber()==4||GameWindow.getLevelNumber()==5)) {
            blockPos = new Point(810, 20);
        }
        forBlock.setForLoopIter((int)forCount.getValue());
        forCount.addChangeListener(c->{forBlock.setForLoopIter((int)forCount.getValue());});
        layout.putConstraint(SpringLayout.NORTH,forCount,blockPos.y+45,SpringLayout.NORTH,this);
        layout.putConstraint(SpringLayout.WEST,forCount,blockPos.x+48,SpringLayout.WEST,this);
        this.repaint();
    }

    void makeAnnouncement() {
        newActionWindow newBlock = new newActionWindow(wrappedPanel.container);
        this.add(newBlock);
        newBlock.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                remove(newBlock);
                repaint();
            }
        });
        SpringLayout layout= (SpringLayout) this.getLayout();
        layout.putConstraint(SpringLayout.WEST, newBlock,150,SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, newBlock,80,SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.EAST, newBlock,-150,SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.SOUTH, newBlock,-20,SpringLayout.SOUTH, this);
        this.revalidate();
        this.repaint();
        //show new block announcement for 10 seconds or until user clicks out
        Timer timer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                remove(newBlock);
                repaint();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void removeForSpinner() {
        this.remove(forCount);
    }

    public void winAnimation(WinAnimationPanel winAnimation) {
        this.removeAll();
        this.setPreferredSize(wrappedPanel.getContainer().getPreferredSize());
        this.add(winAnimation);
        SpringLayout layout= (SpringLayout) this.getLayout();
        layout.putConstraint(SpringLayout.WEST, winAnimation,0,SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, winAnimation,0,SpringLayout.NORTH, this);
        this.validate();
        this.repaint();
        //if location of scoop is passed glass pane, delete
        Timer timer2= new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                winAnimation.repaint();
            }
        });
        Timer timer1 = new Timer(8000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                winAnimation.setVisible(false);
                timer2.setRepeats(false);
                timer2.stop();
            }
        });
        timer1.setRepeats(false);
        timer2.setRepeats(true);
        timer1.start();
        timer2.start();
    }
}
