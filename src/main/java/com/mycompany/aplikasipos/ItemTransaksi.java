/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplikasipos;

/**
 *
 * @author ACER
 */
class ItemTransaksi {
    private Produk produk;
    private int kuantitas;

    public ItemTransaksi(Produk produk, int kuantitas) {
        this.produk = produk;
        this.kuantitas = kuantitas;
    }

    public Produk getProduk() { return produk; }
    public int getKuantitas() { return kuantitas; }
    
    public void tambahKuantitas(int jumlah) { 
        this.kuantitas += jumlah; 
    }

    public double getSubtotal() {
        return produk.getHarga() * kuantitas;
    }
}
