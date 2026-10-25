/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplikasipos;

import java.util.List;
import java.util.ArrayList;
class Transaksi {
    private List<ItemTransaksi> keranjang = new ArrayList<>();

    public void tambahItem(Produk produk, int kuantitas) {
        // Cek apakah produk sudah ada di keranjang, jika ada tambahkan kuantitasnya
        for (ItemTransaksi item : keranjang) {
            if (item.getProduk().getId().equals(produk.getId())) {
                item.tambahKuantitas(kuantitas);
                return;
            }
        }
        keranjang.add(new ItemTransaksi(produk, kuantitas));
    }

    public List<ItemTransaksi> getKeranjang() {
        return keranjang;
    }

    public double hitungTotal() {
        double total = 0;
        for (ItemTransaksi item : keranjang) {
            total += item.getSubtotal();
        }
        return total;
    }

    public boolean isEmpty() {
        return keranjang.isEmpty();
    }

    public void kosongkan() {
        keranjang.clear();
    }
}
