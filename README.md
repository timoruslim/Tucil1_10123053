## Tugas Kecil 1 | Strategi Algoritma (IF2211)

Tugas Kecil 1 untuk mata kuliah Strategi Algoritma (IF2211) berupa IQ Puzzler Pro solver. Permainanan papan IQ Puzzler Pro dengan diberikan papan awal kosong dan beberapa blok diselesaikan dengan algoritma brute force murni tanpa heuristik, yaitu dengan mencoba semua permutasi dari balok pada papan hingga mendapatkan solusi (jika ada).

### Struktur Program

Digunakan bahasa Java. Maka, diperlukan compiler Java dengan JDK (Java Development Kit) perlu terinstall. Berikut langkah-langkah untuk menjalankan program.

1. Clone repository.

```python
git clone https://github.com/timoruslim/Tucil1_10123053
```

2. Navigate to `src` directory.

```python
cd src
```

3. Compile `App.java`.

```python
javac App.java
```

4. Execute `App`.

```python
java App
```

Untuk memudahkan, folder dapat juga dibuka menggunakan IDE seperti Visual Studio Code, Netbeans, atau Eclipse sebagai project, lalu file `App.java` dapat di-run secara langsung.

- `src`: folder untuk source
- `bin`: folder untuk executables
- `test`: folder untuk file test case dan solusinya dalam `.txt`
- `doc`: folder untuk laporan dalam `.pdf`

### Cara Kerja Program

Solver ini berupa program CLI. Berikut cara program menerima input puzzle.

1. Sesuai prompt, isi absolute atau relative path dari file `.txt` yang sesuai.
2. Jika `.txt` sudah berada di folder `test`, cukup tulis nama filenya seperti `nama.txt`.
3. Perhatikan agar `.txt` sudah memiliki format seperti pada spesifikasi Tucil.

Berikut cara program menghasilkan output.

1. Puzzle akan diselesaikan dengan algoritma brute-force (akan diperlukan waktu tergantung kesulitan puzzle).
2. Jika ada solusi, program akan menghasilkan output berupa papan dan letak setiap blok pada papan yang sudah diwarnakan secara unik.
3. Ditampilkan juga waktu yang dimakan dan kasus yang ditelusuri oleh algoritma.
4. Dengan mengikuti prompt terakhir, output tersebut dapat disimpan dalam suatu `.txt` pada folder `test`. File solusi akan memiliki nama sama dengan file input tetapi dengan sufiks `_Solution.txt`.

Contoh input seperti berikut.

```python
5 5 7
DEFAULT
A
AA
B
BB
C
CC
D
DD
EE
EE
E
FF
FF
F
GGG
```

Dengan output seperti berikut.

```python
A B B C C
A A B C D
E E E D D
E E F F F
G G G F F

Waktu pencarian: 74 ms

Banyak kasus yang ditinjau: 17111
```

Untuk penjelasan cara kerja algoritma lebih spesifik secara mendalam, dapat dilihat pada laporan berupa `.pdf` di folder `doc`.

### Authors

Timothy Niels Ruslim (10123053)
