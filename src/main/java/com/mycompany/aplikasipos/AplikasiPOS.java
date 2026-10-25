/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.aplikasipos;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class AplikasiPOS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Produk> daftarProduk = new ArrayList<>();

        // Inisialisasi Data Produk Awal
        daftarProduk.add(new Produk("P01", "Kopi Hitam", 15000, 20));
        daftarProduk.add(new Produk("P02", "Roti Bakar", 12000, 15));
        daftarProduk.add(new Produk("P03", "Es Teh Manis", 5000, 30));
        daftarProduk.add(new Produk("P04", "Mie Goreng", 10000, 25));

        Transaksi transaksi = new Transaksi();
        boolean running = true;

        System.out.println("========================================");
        System.out.println("    APLIKASI KASIR / POS TERMINAL       ");
        System.out.println("========================================");

        while (running) {
            System.out.println("\n--- MENU UTAMA ---");
            System.out.println("1. Lihat Daftar Produk");
            System.out.println("2. Tambah Produk ke Keranjang");
            System.out.println("3. Lihat Keranjang Belanja");
            System.out.println("4. Proses Pembayaran (Checkout)");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-5): ");

            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    System.out.println("\n--- DAFTAR PRODUK ---");
                    for (Produk p : daftarProduk) {
                        p.tampilkanInfo();
                    }
                    break;

                case "2":
                    System.out.println("\n--- TAMBAH KE KERANJANG ---");
                    for (Produk p : daftarProduk) {
                        p.tampilkanInfo();
                    }
                    System.out.print("Masukkan ID Produk: ");
                    String idProduk = scanner.nextLine().trim();

                    Produk produkPilihan = null;
                    for (Produk p : daftarProduk) {
                        if (p.getId().equalsIgnoreCase(idProduk)) {
                            produkPilihan = p;
                            break;
                        }
                    }

                    if (produkPilihan == null) {
                        System.out.println("❌ Produk dengan ID tersebut tidak ditemukan!");
                        break;
                    }

                    System.out.print("Masukkan Jumlah Beli: ");
                    try {
                        int jumlah = Integer.parseInt(scanner.nextLine());
                        if (jumlah <= 0) {
                            System.out.println("❌ Jumlah beli harus lebih dari 0!");
                            break;
                        }
                        if (jumlah > produkPilihan.getStok()) {
                            System.out.println("❌ Stok tidak mencukupi! Sisa stok: " + produkPilihan.getStok());
                            break;
                        }

                        transaksi.tambahItem(produkPilihan, jumlah);
                        System.out.println("✅ Berhasil menambahkan " + produkPilihan.getNama() + " (" + jumlah + ") ke keranjang.");
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Masukkan angka yang valid!");
                    }
                    break;

                case "3":
                    System.out.println("\n--- KERANJANG BELANJA ---");
                    if (transaksi.isEmpty()) {
                        System.out.println("Keranjang masih kosong.");
                    } else {
                        System.out.printf("%-15s | %-10s | %-10s | %-10s\n", "Nama Produk", "Harga", "Qty", "Subtotal");
                        System.out.println("--------------------------------------------------");
                        for (ItemTransaksi item : transaksi.getKeranjang()) {
                            System.out.printf("%-15s | Rp %.0f | %-10d | Rp %.0f\n", 
                                item.getProduk().getNama(), 
                                item.getProduk().getHarga(), 
                                item.getKuantitas(), 
                                item.getSubtotal());
                        }
                        System.out.println("--------------------------------------------------");
                        System.out.printf("TOTAL PEMBAYARAN: Rp %.0f\n", transaksi.hitungTotal());
                    }
                    break;

                case "4":
                    if (transaksi.isEmpty()) {
                        System.out.println("❌ Keranjang kosong, tidak ada yang bisa dibayar.");
                        break;
                    }

                    System.out.println("\n--- PEMBAYARAN ---");
                    double total = transaksi.hitungTotal();
                    System.out.printf("Total Belanja: Rp %.0f\n", total);

                    boolean pembayaranValid = false;
                    while (!pembayaranValid) {
                        System.out.print("Masukkan Jumlah Uang Pembayaran: Rp ");
                        try {
                            double bayar = Double.parseDouble(scanner.nextLine());
                            if (bayar < total) {
                                System.out.println("❌ Uang tidak cukup! Kekurangan: Rp " + (total - bayar));
                            } else {
                                double kembalian = bayar - total;
                                
                                // Kurangi stok produk asli setelah pembayaran sukses
                                for (ItemTransaksi item : transaksi.getKeranjang()) {
                                    item.getProduk().kurangiStok(item.getKuantitas());
                                }

                                System.out.println("\n========================================");
                                System.out.println("          STRUK PEMBELIAN POS           ");
                                System.out.println("========================================");
                                for (ItemTransaksi item : transaksi.getKeranjang()) {
                                    System.out.printf("%-15s x%d : Rp %.0f\n", 
                                        item.getProduk().getNama(), 
                                        item.getKuantitas(), 
                                        item.getSubtotal());
                                }
                                System.out.println("----------------------------------------");
                                System.out.printf("Total    : Rp %.0f\n", total);
                                System.out.printf("Bayar    : Rp %.0f\n", bayar);
                                System.out.printf("Kembalian: Rp %.0f\n", kembalian);
                                System.out.println("========================================");
                                System.out.println("   TERIMA KASIH TELAH BERBELANJA!       ");
                                System.out.println("========================================\n");

                                transaksi.kosongkan(); // Kosongkan keranjang setelah transaksi selesai
                                pembayaranValid = true;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("❌ Masukkan nominal angka uang yang valid!");
                        }
                    }
                    break;

                case "5":
                    running = false;
                    System.out.println("Terima kasih telah menggunakan aplikasi POS ini!");
                    break;

                default:
                    System.out.println("❌ Pilihan tidak valid. Masukkan angka 1 sampai 5.");
            }
        }
        scanner.close();
    }
}