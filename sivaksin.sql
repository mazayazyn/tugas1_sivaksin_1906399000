CREATE TABLE faskes (
    id int not null AUTO_INCREMENT,
    id_vaksin int not null,
    nama varchar(50),
    kuota int,
    jam_mulai varchar(5),
    jam_tutup varchar(5),
    provinsi varchar(50),
    kabupaten varchar(50),
    PRIMARY KEY (id),
    FOREIGN KEY (id_vaksin) REFERENCES vaksin(id)
);

CREATE TABLE vaksin (
    jenis varchar(50),
    asal_negara varchar(50),
    efikasi double,
    id int not null AUTO_INCREMENT,
    PRIMARY KEY (id)
);

CREATE TABLE pasien (
    id int not null AUTO_INCREMENT,
    jenis_kelamin varchar(50),
    tanggal_lahir date,
    nik varchar(30) UNIQUE,
    pekerjaan varchar(30),
    tempat_lahir varchar(20),
    nama varchar(50),
    nomor_telepon varchar(30),
    PRIMARY KEY (id),
    FOREIGN KEY (id_faskes) REFERENCES faskes(id)
);

CREATE TABLE dokter (
    id int not null AUTO_INCREMENT,
    nip varchar(30) UNIQUE,
    nomor_telepon varchar(30),
    nama varchar(50),
    PRIMARY KEY (id),
    FOREIGN KEY (id_vaksin) REFERENCES vaksin(id)
);

CREATE TABLE disuntik (
    batch_id varchar(13) PRIMARY KEY,
    waktu_suntik varchar(5),
    id_pasien int not null,
    id_dokter int not null,
    FOREIGN KEY (id_pasien) REFERENCES pasien(id),
    FOREIGN KEY (id_dokter) REFERENCES dokter(id)
);