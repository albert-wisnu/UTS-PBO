/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplikasipos;

/**
 *
 * @author ACER
 */
class Produk {
    private String id;
    private String nama;
    private double harga;
    private int stok;

    public Produk(String id, String nama, double harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public int getStok() { return stok; }

    public void kurangiStok(int jumlah) {
        this.stok -= jumlah;
    }

    public void tampilkanInfo() {
        System.out.printf("[%s] %-15s | Rp %.0f | Stok: %d\n", id, nama, harga, stok);
    }
}
