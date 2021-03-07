package com.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class TSPMain {

    public static Node aStar(Node start, Node target){
        TreeSet<Node> closedList = new TreeSet<>();
        TreeSet<Node> openList = new TreeSet<>();

        start.f = start.g + start.calculateHeuristic(target);
        openList.add(start);
        System.out.println("Finding path...");

        while(!openList.isEmpty()){
            Node n = openList.first();
            if(n == target){
                return n;
            }

            for(Node.Edge edge : n.neighbors){
                Node m = edge.node;
                double totalWeight = n.g + edge.weight;

                if(!openList.contains(m) && !closedList.contains(m)){
                    m.parent = n;
                    m.g = totalWeight;
                    m.f = m.g + m.calculateHeuristic(target);
                    openList.add(m);
                } else {
                    if(totalWeight < m.g){
                        m.parent = n;
                        m.g = totalWeight;
                        m.f = m.g + m.calculateHeuristic(target);

                        if(closedList.contains(m)){
                            closedList.remove(m);
                            openList.add(m);
                        }
                    }
                }
            }

            openList.remove(n);
            closedList.add(n);
        }
        return null;
    }

    public static void printPath(Node target){
        Node n = target;

        if(n==null) {
            System.out.println("Empty");
            return;
        }

        List<Integer> ids = new ArrayList<>();

        while(n.parent != null){
            ids.add(n.id);
            n = n.parent;
        }
        ids.add(n.id);
        Collections.reverse(ids);

        for(int id : ids){
            System.out.print(id + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {

        Node node1 = new Node(3);
        node1.setName("Negril");

        Node node2 = new Node(3);
        node2.setName("Lucea");

        Node node3 = new Node(2);
        node3.setName("Montego Bay");

        Node node4 = new Node(2);
        node4.setName("Clark's Town");

        Node node5 = new Node(1);
        node5.setName("Ocho Rios");

        Node node6 = new Node(1);
        node6.setName("Port Maria");

        Node node7 = new Node(0);
        node7.setName("Buff Bay");

        Node node8 = new Node(0);
        node8.setName("Port Antonio");

        Node node9 = new Node(0);
        node9.setName("Morant Bay");

        Node node10 = new Node(3);
        node10.setName("Savanna-la-mar");

        Node node11 = new Node(2);
        node11.setName("Black River");

        Node node12 = new Node(2);
        node12.setName("Treasure Beach");

        Node node13 = new Node(2);
        node13.setName("Alligator Pond");

        Node node14 = new Node(1);
        node14.setName("May Pen");

        Node node15 = new Node(1);
        node15.setName("Spanish Town");

        node5.addBranch(60, node15);
        Node node16 = new Node(1);
        node16.setName("Kingston");

        node1.addBranch(30, node2);
        node1.addBranch(30, node10);
        node2.addBranch(41, node3);
        node3.addBranch(70, node4);
        node4.addBranch(50, node5);
        node5.addBranch(30, node6);
        node5.addBranch(60, node15);
        node6.addBranch(45, node7);
        node7.addBranch(50, node8);
        node8.addBranch(60, node9);
        node9.addBranch(87, node16);
        node10.addBranch(45, node3);
        node11.addBranch(21, node13);
        node12.addBranch(21, node11);
        node13.addBranch(36, node14);
        node14.addBranch(15, node15);
        node15.addBranch(30, node16);

        Node head = node1;
        head.g = 0;
        Node target = node15;
        Node res = aStar(head, target);
        printPath(res);
    }
}
