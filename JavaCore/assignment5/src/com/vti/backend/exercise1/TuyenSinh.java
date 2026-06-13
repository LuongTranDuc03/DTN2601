package com.vti.backend.exercise1;

import com.vti.entity.exercise1.Candidate;
import com.vti.entity.exercise1.KhoiA;
import com.vti.entity.exercise1.KhoiB;
import com.vti.entity.exercise1.KhoiC;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TuyenSinh implements ITuyenSinh {
    private List<Candidate> candidates;
    private Scanner scanner;

    public TuyenSinh() {
        candidates = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    @Override
    public void addCandidate() {
        System.out.println("Nhap thong tin thi sinh:");
        System.out.print("So bao danh: ");
        String sbd = scanner.nextLine();
        System.out.print("Ho ten: ");
        String name = scanner.nextLine();
        System.out.print("Dia chi: ");
        String address = scanner.nextLine();
        System.out.print("Muc uu tien: ");
        int priority;
        try {
            priority = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            priority = 0;
        }

        System.out.println("Chon khoi thi (A/B/C):");
        String khoi = scanner.nextLine().toUpperCase();

        Candidate candidate = null;
        switch (khoi) {
            case "A":
                candidate = new KhoiA(sbd, name, address, priority);
                break;
            case "B":
                candidate = new KhoiB(sbd, name, address, priority);
                break;
            case "C":
                candidate = new KhoiC(sbd, name, address, priority);
                break;
            default:
                System.out.println("Khoi khong hop le!");
                return;
        }
        candidates.add(candidate);
        System.out.println("Them thi sinh thanh cong!");
    }

    @Override
    public void showInfo() {
        if (candidates.isEmpty()) {
            System.out.println("Danh sach thi sinh trong.");
            return;
        }
        for (Candidate c : candidates) {
            System.out.println(c);
        }
    }

    @Override
    public void searchBySBD() {
        System.out.print("Nhap so bao danh can tim: ");
        String sbd = scanner.nextLine();
        boolean found = false;
        for (Candidate c : candidates) {
            if (c.getSoBaoDanh().equalsIgnoreCase(sbd)) {
                System.out.println(c);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay thi sinh co SBD: " + sbd);
        }
    }
}
