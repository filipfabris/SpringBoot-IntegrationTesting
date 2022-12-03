//package opp;
//
//import opp.model.*;
//import opp.model.Security.Privilege;
//import opp.model.Security.Role;
//import opp.repository.dao.PrivilegeRepository;
//import opp.repository.dao.RoleRepository;
//import opp.service.*;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.*;
//
//import opp.service.KorisnikService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import javax.transaction.Transactional;
//
///**
// * Example component used to insert some test students at application startup.
// */
//@Component
//public class DataInitializer {
//    @Autowired
//    private OglasService Oglasservice;
//
//    @Autowired
//    private KategorijaService kategorijaService;
//
//    @Autowired
//    private PodKategorijaService podkategorijaService;
//
//    @Autowired
//    private KorisnikService korisnikService;
//
//    @Autowired
//    private AdresaService adresaService;
//
//    @Autowired
//    private PrivilegeRepository privilegeRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//
//    @EventListener
//    public void appReady(ApplicationReadyEvent event) throws IOException {
//        generateKorisnik();
//        generateAdmin();
//
//        generateKategorija();
//        generateOglasi();
//
//        Privilege readPrivilege
//                = createPrivilegeIfNotFound("READ_PRIVILEGE");
//        Privilege writePrivilege
//                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
//
//        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
//
//        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
//        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
//
//        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
//        Role userRole = roleRepository.findByName("ROLE_USER");
//
//        for(Korisnik tmp: korisnikService.findAll()){
//            if(tmp.getEmail().equals( "admin" )){
//                tmp.setRoles(Arrays.asList(adminRole));
//                korisnikService.saveKorisnik( tmp );
//            }else{
////                tmp.setRoles(Arrays.asList(userRole));
//                korisnikService.saveKorisnik( tmp );
//            }
//        }
//
//    }
//
//    private void generateOglasi() throws IOException {
//        String pathToPictures = "src/main/resources/slike/";
//
//        String naslov = "Doniram lego star wars set";
//        String opis = "Radi se Imperial TIE Fighter 75300 setu, originalna kutija i upute uključene.";
//        byte[] picInBytes = getPicture(pathToPictures + "lego-tie-fighter.jpeg");
//        Calendar datumObjave = Calendar.getInstance();
//        datumObjave.set(2022, 11,13);
//        StanjePredmeta stanje = StanjePredmeta.MALO_KORIŠTENO;
//        String predvidenaDob = "7";
//        Spol predivdenSpol = Spol.UNISEX;
//        Calendar rokUpotrebe = null;
//        Podkategorija podkategorija = podkategorijaService.getByNaziv("Lego kocke");
//        Korisnik korisnik = korisnikService.findByEmail("mario.markovic@gmail.com");
//
//        Oglas oglas = new Oglas(naslov, opis, picInBytes, datumObjave, stanje, predvidenaDob, predivdenSpol, rokUpotrebe,podkategorija, korisnik);
//        Oglasservice.saveOglas( oglas );
//
//
//        naslov = "3 u 1 kolica srednje veličine";
//        opis = "Kolica su vrlo praktična i izvrsnog omjera cijene i kvalitete. crna .";
//        picInBytes = getPicture(pathToPictures + "3u1-kolica.jpg");
//        datumObjave = Calendar.getInstance();
//        datumObjave.set(2022, 6,23);
//        stanje = StanjePredmeta.JAKO_KORIŠTENO;
//        predvidenaDob = "1";
//        predivdenSpol = Spol.UNISEX;
//        rokUpotrebe = null;
//        podkategorija = podkategorijaService.getByNaziv("3 u 1");
//        korisnik = null;
//
//        oglas = new Oglas(naslov, opis, picInBytes, datumObjave, stanje, predvidenaDob, predivdenSpol, rokUpotrebe,podkategorija, korisnik);
//        Oglasservice.saveOglas( oglas );
//
//
//        naslov = "Plišana igračka pikachu";
//        opis = "Doniran plišanu igračku pikachu, moj sin ju je prerastao i više nemamo potrebe za njom. Veličina igračke je 20 cm.";
//        picInBytes = getPicture(pathToPictures + "pikachu.jpg");
//        datumObjave = Calendar.getInstance();
//        datumObjave.set(2022, 10,6);
//        stanje = StanjePredmeta.SREDNJE_KORIŠTENO;
//        predvidenaDob = "1";
//        predivdenSpol = Spol.UNISEX;
//        rokUpotrebe = null;
//        podkategorija = podkategorijaService.getByNaziv("Plišane igračke");
//        korisnik = korisnikService.findByEmail("mario.markovic@gmail.com");
//
//        oglas = new Oglas(naslov, opis, picInBytes, datumObjave, stanje, predvidenaDob, predivdenSpol, rokUpotrebe,podkategorija, korisnik);
//        Oglasservice.saveOglas( oglas );
//
//        naslov = "Majca za djevojčice";
//        opis = "majca za djevojčice veličine 104";
//        picInBytes = getPicture(pathToPictures + "majca.jpg");
//        datumObjave = Calendar.getInstance();
//        datumObjave.set(2022, 11,11);
//        stanje = StanjePredmeta.NOVO;
//        predvidenaDob = "3";
//        predivdenSpol = Spol.Ž;
//        rokUpotrebe = null;
//        podkategorija = podkategorijaService.getByNaziv("Majce");
//        korisnik = null;
//
//        oglas = new Oglas(naslov, opis, picInBytes, datumObjave, stanje, predvidenaDob, predivdenSpol, rokUpotrebe,podkategorija, korisnik);
//        Oglasservice.saveOglas( oglas );
//
//    }
//
//    private void generateKategorija() {
//        // Igračke
//        Kategorija kategorija = new Kategorija();
//        kategorija.setNaziv( "Igračke" );
//        kategorijaService.saveKategorija( kategorija );
//
//        Podkategorija podkategorija = new Podkategorija();
//        podkategorija.setNaziv( "Lego kocke" );
//        podkategorija.setKategorija( kategorija );
//        podkategorijaService.savePodKategorija( podkategorija );
//
//         podkategorija = new Podkategorija();
//        podkategorija.setNaziv( "Plišane igračke" );
//        podkategorija.setKategorija( kategorija );
//        podkategorijaService.savePodKategorija( podkategorija );
//
//         podkategorija = new Podkategorija();
//        podkategorija.setNaziv( "Lutke" );
//        podkategorija.setKategorija( kategorija );
//        podkategorijaService.savePodKategorija( podkategorija );
//
//        kategorija = new Kategorija();
//        kategorija.setNaziv( "Kolica" );
//        kategorijaService.saveKategorija( kategorija );
//
//         podkategorija = new Podkategorija();
//        podkategorija.setNaziv( "3 u 1" );
//        podkategorija.setKategorija( kategorija );
//        podkategorijaService.savePodKategorija( podkategorija );
//
//        podkategorija = new Podkategorija();
//        podkategorija.setNaziv( "Kišobran kolica" );
//        podkategorija.setKategorija( kategorija );
//        podkategorijaService.savePodKategorija( podkategorija );
//
//        podkategorija = new Podkategorija();
//        podkategorija.setNaziv( "Dodaci za kolica" );
//        podkategorija.setKategorija( kategorija );
//        podkategorijaService.savePodKategorija( podkategorija );
//
//        kategorija = new Kategorija();
//        kategorija.setNaziv( "Namještaj" );
//        kategorijaService.saveKategorija( kategorija );
//
//        podkategorija = new Podkategorija();
//        podkategorija.setNaziv( "Kreveti" );
//        podkategorija.setKategorija( kategorija );
//        podkategorijaService.savePodKategorija( podkategorija );
//
//        podkategorija = new Podkategorija();
//        podkategorija.setNaziv( "Hranilice" );
//        podkategorija.setKategorija( kategorija );
//        podkategorijaService.savePodKategorija( podkategorija );
//
//        podkategorija = new Podkategorija();
//        podkategorija.setNaziv( "Komode za prematenje" );
//        podkategorija.setKategorija( kategorija );
//        podkategorijaService.savePodKategorija( podkategorija );
//
//        kategorija = new Kategorija();
//        kategorija.setNaziv( "Odjeća" );
//        kategorijaService.saveKategorija( kategorija );
//
//        podkategorija = new Podkategorija();
//        podkategorija.setNaziv( "Majce" );
//        podkategorija.setKategorija( kategorija );
//        podkategorijaService.savePodKategorija( podkategorija );
//
//        podkategorija = new Podkategorija();
//        podkategorija.setNaziv( "Hlače" );
//        podkategorija.setKategorija( kategorija );
//        podkategorijaService.savePodKategorija( podkategorija );
//
//        podkategorija = new Podkategorija();
//        podkategorija.setNaziv( "Cipele" );
//        podkategorija.setKategorija( kategorija );
//        podkategorijaService.savePodKategorija( podkategorija );
//
//        kategorija = new Kategorija();
//        kategorija.setNaziv( "Razno" );
//        kategorijaService.saveKategorija( kategorija );
//
//    }
//
//    private Korisnik generateKorisnik() {
//        Korisnik korisnik = new Korisnik();
//        korisnik.setIme( "Mario" );
//        korisnik.setPrezime( "Markovic" );
//        korisnik.setEmail( "mario.markovic@gmail.com" );
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode("123456");
//        korisnik.setLozinka(encodedPassword);
//        korisnik.setAdresa( generateAdresa() );
//        korisnik.setBrojMobitela( "0987654321" );
//        korisnik.getAdresa().setKorisnici( korisnik );
//		korisnikService.saveKorisnik( korisnik );
//        return korisnik;
//    }
//
//    private Korisnik generateAdmin() {
//        Korisnik korisnik = new Korisnik();
//        korisnik.setIme( "Admin" );
//        korisnik.setPrezime( "Admin" );
//        korisnik.setEmail( "admin" );
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode("admin");
//        korisnik.setLozinka(encodedPassword);
//        korisnik.setBrojMobitela( "000000000" );
//        korisnikService.saveKorisnik( korisnik );
//        return korisnik;
//    }
//
//    private Adresa generateAdresa() {
//        Adresa adresa = new Adresa();
//        adresa.setGrad( "Zagreb" );
//        adresa.setUlica( "Trg bana Josipa Jelacica" );
//        adresa.setZipcode( "10000" );
//        return adresa;
//    }
//
//    private byte[] getPicture(String path) throws IOException {
//        File file = new File(path);
//        byte[] picInBytes = new byte[(int) file.length()];
//        FileInputStream fileInputStream = new FileInputStream(file);
//        fileInputStream.read(picInBytes);
//        fileInputStream.close();
//        return picInBytes;
//    }
//
//
//    @Transactional
//    Privilege createPrivilegeIfNotFound(String name) {
//
//        Privilege privilege = privilegeRepository.findByName(name);
//        if (privilege == null) {
//            privilege = new Privilege(name);
//            privilegeRepository.save(privilege);
//        }
//        return privilege;
//    }
//
//    @Transactional
//    Role createRoleIfNotFound(
//            String name, Collection<Privilege> privileges) {
//
//        Role role = roleRepository.findByName(name);
//        if (role == null) {
//            role = new Role(name);
//            role.setPrivileges(privileges);
//            roleRepository.save(role);
//        }
//        return role;
//    }
//
//
//}
