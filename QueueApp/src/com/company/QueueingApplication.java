package com.company;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class QueueingApplication extends JApplet {
    Dimension screenSize;
    JPanel rootPanel;
    JPanel topPanel;
    JPanel bottomPanel;
    JPanel blPanel;
    JPanel brPanel;
    JPanel tlPanel;
    JPanel trPanel;

    JPanel dispCashier;
    JPanel dispService;

    JPanel regSenior;
    JPanel cashService;
    JPanel enterQueue;
    JPanel dispNumber;
    JPanel queueNext;

    JLabel titleCashier;
    JLabel titleService;
    JLabel nameCashier;
    JLabel nameService;
    JLabel numCashier;
    JLabel numService;

    JLabel regular;
    JLabel senior;
    JLabel pwd;
    JLabel cashier;
    JLabel service;
    JLabel nameLabel;
    JLabel queue;
    JLabel cancel;
    JLabel errorMsg;
    JLabel title;
    JLabel queueName;
    JLabel queueNumber;
    JLabel getNewQueue;
    JLabel cashierNext;
    JLabel serviceNext;

    JTextField nameText;

    SetQueueing q  = new SetQueueing();

    String status = "";
    String name = "";
    String transactionType = "";
    String displayName = "";
    String displayNum = "";



    public void init(){
        initPanel();
    }

    public void initPanel(){
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        this.setSize(width, height);
        //add rootPanel to window
        rootPanel = new JPanel();
        rootPanel.setSize(width, height);
        this.add(rootPanel);
        //setLayout for rootPanel
        rootPanel.setLayout(new GridLayout(2, 1));
        //init topPanel
        topPanel = new JPanel(){
            public void paintComponent(Graphics g) {
                Image img = Toolkit.getDefaultToolkit().getImage(
                        QueueingApplication.class.getResource("/images/topGray.png"));
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };

        topPanel.setLayout(new GridLayout(1, 2));
        rootPanel.add(topPanel);

        tlPanel = new JPanel();
        tlPanel.setLayout(null);
        tlPanel.setOpaque(false);
        topPanel.add(tlPanel);

        trPanel = new JPanel();
        trPanel.setLayout(null);
        trPanel.setOpaque(false);
        topPanel.add(trPanel);

        dispCashier = new JPanel();
        dispCashier.setSize(300, 300);
        dispCashier.setOpaque(false);
        tlPanel.add(dispCashier);

        dispService = new JPanel();
        dispService.setSize(300, 300);
        dispService.setOpaque(false);
        trPanel.add(dispService);


        //bottomPanel
        bottomPanel = new JPanel(){
            public void paintComponent(Graphics g) {
                Image img = Toolkit.getDefaultToolkit().getImage(
                        QueueingApplication.class.getResource("/images/botGray.png"));
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        bottomPanel.setLayout(new GridLayout(1,2));
        rootPanel.add(bottomPanel);

        blPanel = new JPanel();
        blPanel.setLayout(null);
        blPanel.setOpaque(false);
        bottomPanel.add(blPanel);

        brPanel = new JPanel();
        brPanel.setLayout(null);
        brPanel.setOpaque(false);
        bottomPanel.add(brPanel);

        regSenior = new JPanel();
        regSenior.setSize(300, 300);
        regSenior.setOpaque(false);
        blPanel.add(regSenior);

        cashService = new JPanel();
        cashService.setSize(300, 195);
        cashService.setOpaque(false);
        blPanel.add(cashService);

        enterQueue = new JPanel();
        enterQueue.setSize(300, 400);
        enterQueue.setOpaque(false);
        enterQueue.setVisible(false);
        blPanel.add(enterQueue);

        dispNumber = new JPanel();
        dispNumber.setSize(300, 300);
        dispNumber.setOpaque(false);
        blPanel.add(dispNumber);

        queueNext = new JPanel();
        queueNext.setSize(300, 195);
        queueNext.setOpaque(false);
        brPanel.add(queueNext);

        cashService.setVisible(false);
        enterQueue.setVisible(false);
        dispNumber.setVisible(false);
        regSenior.setVisible(true);

        addTopPanelComponents();
        addRegSenior();
        addCashService();
        addQueue();
        addDispNumber();
        addQueueNext();
        addEvents();
        setPanelLocations();
        adjustLocationSizes();
    }
    public void setPanelLocations(){
        regSenior.setLocation((blPanel.getWidth() - regSenior.getWidth()) / 2, (blPanel.getHeight() - regSenior.getHeight()) / 2);
        cashService.setLocation((blPanel.getWidth() - cashService.getWidth()) / 2, (blPanel.getHeight() - cashService.getHeight()) / 2);
        enterQueue.setLocation((blPanel.getWidth() - enterQueue.getWidth()) / 2, (blPanel.getHeight() - enterQueue.getHeight()) / 2);
        dispNumber.setLocation((blPanel.getWidth() - dispNumber.getWidth()) / 2, (blPanel.getHeight() - dispNumber.getHeight()) / 2);
        queueNext.setLocation((brPanel.getWidth() - queueNext.getWidth()) / 2, (brPanel.getHeight() - queueNext.getHeight()) / 2);
        dispCashier.setLocation((tlPanel.getWidth() - dispCashier.getWidth()) / 2, ((tlPanel.getHeight() - dispCashier.getHeight()) / 2)-20);
        dispService.setLocation((trPanel.getWidth() - dispService.getWidth()) / 2, ((trPanel.getHeight() - dispService.getHeight()) / 2)-20);
    }

    public void adjustLocationSizes(){
        this.addComponentListener( new ComponentAdapter() {
            @Override
            public void componentResized( ComponentEvent e ) {
                setPanelLocations();
            }
        } );
    }
    public void addTopPanelComponents(){
        dispCashier.setLayout(null);
        dispService.setLayout(null);
        titleCashier = new JLabel("Cashier");
        titleService = new JLabel("Service");
        nameCashier = new JLabel();
        nameService = new JLabel();
        numCashier = new JLabel();
        numService = new JLabel();


        titleCashier.setSize(dispCashier.getWidth(), 60);
        titleCashier.setHorizontalAlignment(SwingConstants.CENTER);
        titleCashier.setForeground(new Color(95, 179, 55));
        titleCashier.setLocation((dispCashier.getWidth() - titleCashier.getWidth()) / 2, (int)(dispCashier.getHeight() * 0.20));
        titleCashier.setFont(new Font("SansSerif", Font.BOLD, 60));

        titleService.setSize(dispService.getWidth(), 60);
        titleService.setHorizontalAlignment(SwingConstants.CENTER);
        titleService.setForeground(new Color(95, 179, 55));
        titleService.setLocation((dispService.getWidth() - titleService.getWidth()) / 2, (int)(dispService.getHeight() * 0.20));
        titleService.setFont(new Font("SansSerif", Font.BOLD, 60));

        nameCashier.setSize(dispCashier.getWidth(), 55);
        nameCashier.setHorizontalAlignment(SwingConstants.CENTER);
        nameCashier.setForeground(new Color(238, 238, 238));
        nameCashier.setLocation((dispCashier.getWidth() - nameCashier.getWidth()) / 2, (int)(dispCashier.getHeight() * 0.45));
        nameCashier.setFont(new Font("SansSerif", Font.BOLD, 50));

        nameService.setSize(dispService.getWidth(), 55);
        nameService.setHorizontalAlignment(SwingConstants.CENTER);
        nameService.setForeground(new Color(238, 238, 238));
        nameService.setLocation((dispService.getWidth() - nameService.getWidth()) / 2, (int)(dispService.getHeight() * 0.45));
        nameService.setFont(new Font("SansSerif", Font.BOLD, 50));

        numCashier.setSize(dispCashier.getWidth(), 55);
        numCashier.setHorizontalAlignment(SwingConstants.CENTER);
        numCashier.setForeground(new Color(238, 238, 238));
        numCashier.setLocation((dispCashier.getWidth() - numCashier.getWidth()) / 2, (int)(dispCashier.getHeight() * 0.65));
        numCashier.setFont(new Font("SansSerif", Font.BOLD, 50));

        numService.setSize(dispService.getWidth(), 55);
        numService.setHorizontalAlignment(SwingConstants.CENTER);
        numService.setForeground(new Color(238, 238, 238));
        numService.setLocation((dispService.getWidth() - numService.getWidth()) / 2, (int)(dispService.getHeight() * 0.65));
        numService.setFont(new Font("SansSerif", Font.BOLD, 50));

        dispCashier.add(titleCashier);
        dispService.add(titleService);
        dispCashier.add(nameCashier);
        dispService.add(nameService);
        dispCashier.add(numCashier);
        dispService.add(numService);

    }

    //setting up the regular, senior and pwd buttons
    public void addRegSenior(){
        regSenior.setLayout(new GridLayout(3, 1, 0, 40));
        regular = new JLabel("Regular");
        senior = new JLabel("Senior");
        pwd = new JLabel("PWD/Pregnant");


        regular.setSize(300, 70);
        senior.setSize(300, 70);
        pwd.setSize(300, 70);

        regular.setIcon(new ImageIcon(getClass().getResource("/images/btnRegular.png")));
        senior.setIcon(new ImageIcon(getClass().getResource("/images/btnSenior.png")));
        pwd.setIcon(new ImageIcon(getClass().getResource("/images/btnPwd.png")));

        clickEffects(regular, "/images/btnRegularClicked.png", "/images/btnRegular.png");
        clickEffects(senior, "/images/btnSeniorClicked.png", "/images/btnSenior.png");
        clickEffects(pwd, "/images/btnPwdClicked.png", "/images/btnPwd.png");

        regSenior.add(regular);
        regSenior.add(senior);
        regSenior.add(pwd);
    }
    //setting up the buttons for cashier and service
    public void addCashService(){
        cashService.setLayout(new GridLayout(2, 1, 0, 40));
        cashier = new JLabel("Cashier");
        service = new JLabel("Service");

        cashier.setIcon(new ImageIcon(getClass().getResource("/images/btnCashier.png")));
        service.setIcon(new ImageIcon(getClass().getResource("/images/btnService.png")));

        clickEffects(cashier, "/images/btnCashierClicked.png", "/images/btnCashier.png");
        clickEffects(service, "/images/btnServiceClicked.png", "/images/btnService.png");

        cashService.add(cashier);
        cashService.add(service);
    }
    //setting up the add queue; input part of the queue
    public void addQueue(){
        enterQueue.setLayout(null);
        nameLabel = new JLabel("Enter Name");
        errorMsg = new JLabel();
        nameText = new JTextField();
        queue = new JLabel("Get Number");
        cancel = new JLabel("Cancel");

        nameLabel.setSize(enterQueue.getWidth(), 60);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setLocation((enterQueue.getWidth() - nameLabel.getWidth()) / 2, (int)(enterQueue.getHeight() * 0.10));
        nameLabel.setForeground(new Color(238, 238, 238));
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 50));

        errorMsg.setSize(enterQueue.getWidth(), 40);
        errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
        errorMsg.setLocation((enterQueue.getWidth() - nameLabel.getWidth()) / 2, (int)(enterQueue.getHeight() * 0.70));
        errorMsg.setForeground(new Color(254, 49, 93));
        errorMsg.setFont(new Font("SansSerif", Font.BOLD, 25));

        nameText.setSize((int)(enterQueue.getWidth() * 0.8), 40);
        nameText.setLocation((enterQueue.getWidth() - nameText.getWidth()) / 2, (int)(enterQueue.getHeight() * 0.30));
        nameText.setHorizontalAlignment(SwingConstants.CENTER);
        nameText.setFont(new Font("SansSerif", Font.BOLD, 25));

        queue.setSize(130, 40);
        queue.setIcon(new ImageIcon(getClass().getResource("/images/btnGetNum.png")));
        queue.setLocation((enterQueue.getWidth() - queue.getWidth()) / 2, (int)(enterQueue.getHeight() * 0.46));

        cancel.setSize(130, 40);
        cancel.setIcon(new ImageIcon(getClass().getResource("/images/btnCancel.png")));
        cancel.setLocation((enterQueue.getWidth() - cancel.getWidth()) / 2, (int)(enterQueue.getHeight() * 0.60));

        clickEffects(queue, "/images/btnGetNumClicked.png", "/images/btnGetNum.png");
        clickEffects(cancel, "/images/btnCancelClicked.png", "/images/btnCancel.png");

        enterQueue.add(nameLabel);
        enterQueue.add(errorMsg);
        enterQueue.add(nameText);
        enterQueue.add(queue);
        enterQueue.add(cancel);
    }
    //setting up display after queueing
    public void addDispNumber(){
        dispNumber.setLayout(null);
        title = new JLabel("Your number is");
        queueName = new JLabel();
        queueNumber = new JLabel();

        title.setSize(dispNumber.getWidth(), 45);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(new Color(95, 179, 55));
        title.setLocation((dispNumber.getWidth() - title.getWidth()) / 2, (int)(dispNumber.getHeight() * 0.10));
        title.setFont(new Font("SansSerif", Font.BOLD, 40));

        queueName.setSize(dispNumber.getWidth(), 50);
        queueName.setHorizontalAlignment(SwingConstants.CENTER);
        queueName.setForeground(new Color(238, 238, 238));
        queueName.setLocation((dispNumber.getWidth() - queueName.getWidth()) / 2, (int)(dispNumber.getHeight() * 0.25));
        queueName.setFont(new Font("SansSerif", Font.BOLD, 40));

        queueNumber.setSize(dispNumber.getWidth(), 60);
        queueNumber.setHorizontalAlignment(SwingConstants.CENTER);
        queueNumber.setForeground(new Color(238, 238, 238));
        queueNumber.setLocation((dispNumber.getWidth() - queueNumber.getWidth()) / 2, (int)(dispNumber.getHeight() * 0.45));
        queueNumber.setFont(new Font("SansSerif", Font.BOLD, 50));

        getNewQueue = new JLabel();
        getNewQueue.setSize(130, 40);
        getNewQueue.setIcon(new ImageIcon(getClass().getResource("/images/btnGetNewNum.png")));
        getNewQueue.setLocation((dispNumber.getWidth() - getNewQueue.getWidth()) / 2, (int)(dispNumber.getHeight() * 0.70));
        clickEffects(getNewQueue, "/images/btnGetNewNumClicked.png", "/images/btnGetNewNum.png");

        dispNumber.add(title);
        dispNumber.add(queueName);
        dispNumber.add(queueNumber);
        dispNumber.add(getNewQueue);

    }
    //setting up the bottom right panel and adding components
    public void addQueueNext(){
        queueNext.setLayout(new GridLayout(2, 1, 0, 50));

        cashierNext = new JLabel("Queue Cashier");
        serviceNext = new JLabel("Queue Service");

        cashierNext.setIcon(new ImageIcon(getClass().getResource("/images/btnQueueCashier.png")));
        serviceNext.setIcon(new ImageIcon(getClass().getResource("/images/btnQueueService.png")));

        clickEffects(cashierNext, "/images/btnQueueCashierClicked.png", "/images/btnQueueCashier.png");
        clickEffects(serviceNext, "/images/btnQueueServiceClicked.png", "/images/btnQueueService.png");

        queueNext.add(cashierNext);
        queueNext.add(serviceNext);
    }
    //click effects button background gets white when clicking
    public void clickEffects(JLabel btn, String img, String back){
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                btn.setIcon(new ImageIcon(getClass().getResource(img)));
            }
            public void mouseReleased(MouseEvent mouseEvent){
                btn.setIcon(new ImageIcon(getClass().getResource(back)));
            }
        });
    }
    //setting up mouseClicked Listener for every button
    public void addEvents(){
        regular.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                regSeniorEvent("Regular");
            }
        });
        senior.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                regSeniorEvent("Priority");
            }
        });
        pwd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                regSeniorEvent("Priority");
            }
        });
        cashier.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                cashServiceEvent("Cashier");
            }
        });
        service.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                cashServiceEvent("Service");
            }
        });
        queue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                queueEvent("Get Number");
            }
        });
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                queueEvent("Cancel");
                errorMsg.setText("");
            }
        });
        getNewQueue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                dispNumberEvent(true);
                errorMsg.setText("");
            }
        });
        cashierNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                queueNextEvent("Queue Cashier");
            }
        });
        serviceNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                queueNextEvent("Queue Service");
            }
        });

    }
    //event when clicking the regular, senior or pwd
    public void regSeniorEvent(String type){
        status= type;
        regSenior.setVisible(false);
        cashService.setVisible(true);
    }
    //event for clicking the cashier or service
    public void cashServiceEvent(String transType){
        transactionType = transType;
        enterQueue.setVisible(true);
        cashService.setVisible(false);
    }
    //event for name input and storing the queued numbers in the SetQueueing Class
    public void queueEvent(String a){
        name = nameText.getText();
        if(a.equals("Cancel")) {
            name = "";
            status = "";
            nameText.setText("");
            enterQueue.setVisible(false);
            cashService.setVisible(false);
            regSenior.setVisible(true);
        }
        else {
            if(name.equals("")){
                errorMsg.setText("Please enter name");
            }
            else{
                q.addQueue(name, status, transactionType);
                queueName.setText(name);
                queueNumber.setText(q.getdisplayNumber());
                nameText.setText("");
                enterQueue.setVisible(false);
                dispNumber.setVisible(true);
            }
        }

    }
    //display number and name in the bottom part after queueing
    public void dispNumberEvent(boolean isTrue){
        if(isTrue){
            dispNumber.setVisible(false);
            regSenior.setVisible(true);
        }
    }
    //event for clicking the Queue Cashier and Queue Service
    public void queueNextEvent(String transactionType){
        if(transactionType.equals("Queue Cashier")){
            displayName = q.getQueueName(transactionType);
            displayNum = q.getQueue(transactionType);
            nameCashier.setText(displayName);
            numCashier.setText(displayNum);
        }
        else {
            displayName = q.getQueueName(transactionType);
            displayNum = q.getQueue(transactionType);
            nameService.setText(displayName);
            numService.setText(displayNum);
        }
    }

}
//Class for setting and getting the queue
class SetQueueing{
    private ArrayList<Integer> regularCashierQueue;
    private ArrayList <Integer> priorityCashierQueue;
    private ArrayList <Integer> regularServiceQueue;
    private ArrayList <Integer> priorityServiceQueue;

    private ArrayList <String> regularCashierName;
    private ArrayList <String> priorityCashierName;
    private ArrayList <String> regularServiceName;
    private ArrayList <String> priorityServiceName;

    private int lastRegularCashier;
    private int lastPriorityCashier;
    private int lastRegularService;
    private int lastPriorityService;

    private String number = "";

    public SetQueueing(){
        regularCashierQueue = new ArrayList<Integer>();
        priorityCashierQueue = new ArrayList<Integer>();
        regularServiceQueue= new ArrayList<Integer>();
        priorityServiceQueue = new ArrayList<Integer>();

        regularCashierName = new ArrayList<String>();
        priorityCashierName = new ArrayList<String>();
        regularServiceName = new ArrayList<String>();
        priorityServiceName = new ArrayList<String>();

        lastRegularCashier = 100;
        lastRegularService = 100;
        lastPriorityCashier = 100;
        lastPriorityService = 100;
    }
    // add names
    public void addRegularCashierName(String name){
        regularCashierName.add(name);
    }
    public void addRegularServiceName(String name){
        regularServiceName.add(name);
    }
    public void addPriorityCashierName(String name){
        priorityCashierName.add(name);
    }
    public void addPriorityServiceName(String name){
        priorityServiceName.add(name);
    }

    //add number
    public void addRegularCashier(int last){
        regularCashierQueue.add(last + 1);
    }
    public void addRegularService(int last){
        regularServiceQueue.add(last + 1);
    }
    public void addPriorityCashier(int last){
        priorityCashierQueue.add(last + 1);
    }
    public void addPriorityService(int last){
        priorityServiceQueue.add(last + 1);
    }

    //set last
    public void setLastRegularCashier(){
        lastRegularCashier++;
    }
    public void setLastRegularService(){
        lastRegularService++;
    }
    public void setLastPriorityCashier(){
        lastPriorityCashier++;
    }
    public void setLastPriorityService(){
        lastPriorityService++;
    }

    //getLast
    public int getLastRegularCashier(){
        return lastRegularCashier;
    }
    public int getLastRegularService(){
        return lastRegularService;
    }
    public int getLastPriorityCashier(){
        return lastPriorityCashier;
    }
    public int getLastPriorityService(){
        return lastPriorityService;
    }

    //addToDisplayNumber
    public void setdisplayNumber(String code, int last){
        number = code + last;
    }
    public String getdisplayNumber(){
        return number;
    }
    //add
    public void addQueue(String name, String type, String transactionType){
        if(type.equals("Regular")){
            if(transactionType.equals("Cashier")){
                addRegularCashierName(name);
                addRegularCashier(getLastRegularCashier());
                setLastRegularCashier();
                setdisplayNumber("RC", getLastRegularCashier());
            }
            else{
                addRegularServiceName(name);
                addRegularService(getLastRegularService());
                setLastRegularService();
                setdisplayNumber("RS", getLastRegularService());
            }
        }
        else{
            if(transactionType.equals("Cashier")){
                addPriorityCashierName(name);
                addPriorityCashier(getLastPriorityCashier());
                setLastPriorityCashier();
                setdisplayNumber("PC", getLastPriorityCashier());
            }
            else {
                addPriorityServiceName(name);
                addPriorityService(getLastPriorityService());
                setLastPriorityService();
                setdisplayNumber("PS", getLastPriorityService());
            }
        }
    }

    //get and delete queue number
    public String getQueue(String transaction){
        String code = "";
        int number = 0;

        if(transaction.equals("Queue Cashier")){
            if(priorityCashierQueue.size() > 0 || regularCashierQueue.size() > 0) {
                if (priorityCashierQueue.size() > 0) {
                    code = "PC";
                    number = priorityCashierQueue.get(0);
                    priorityCashierQueue.remove(0);
                } else {
                    code = "RC";
                    number = regularCashierQueue.get(0);
                    regularCashierQueue.remove(0);
                }
            }
            else {
                return "EMPTY";
            }
        }
        else{
            if(priorityServiceQueue.size() > 0 || regularServiceQueue.size() > 0) {
                if (priorityServiceQueue.size() > 0) {
                    code = "PS";
                    number = priorityServiceQueue.get(0);
                    priorityServiceQueue.remove(0);
                } else {
                    code = "RS";
                    number = regularServiceQueue.get(0);
                    regularServiceQueue.remove(0);
                }
            }
            else {
                return "EMPTY";
            }
        }
        return code + number;
    }

    public String getQueueName(String transaction){
        String name = "";
        if(transaction.equals("Queue Cashier")){
            if(priorityCashierName.size() > 0 || regularCashierName.size() >0) {
                if (priorityCashierName.size() > 0) {
                    name = priorityCashierName.get(0);
                    priorityCashierName.remove(0);
                } else {
                    name = regularCashierName.get(0);
                    regularCashierName.remove(0);
                }
            }
            else {
                return "EMPTY";
            }
        }
        else{
            if(priorityServiceName.size() > 0 || regularServiceName.size() > 0) {
                if (priorityServiceName.size() > 0) {
                    name = priorityServiceName.get(0);
                    priorityServiceName.remove(0);
                } else {
                    name = regularServiceName.get(0);
                    regularServiceName.remove(0);
                }
            }
            else {
                return "EMPTY";
            }
        }
        return name;
    }
}
