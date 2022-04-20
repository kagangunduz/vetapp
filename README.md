

# Veteriner Kliniği Uygulaması: VetApp :paw_prints::cat::dog:
---
## Açıklama :page_facing_up:
Spring Boot ve Thymeleaf ile geliştirdiğim basit bir veteriner klinik uygulaması.

Uygulamada şu anda temel olarak hayvan sahibi ve hayvan olmak üzere iki temel yapı mevcut. Bunlar üzerinde aşağıdaki işlemler yapılabilir.

- Ekleme
- Düzenleme
- Silme
- İlişkilendirme
  
---
## Kullanılan Teknolojiler :computer:
- Spring Boot
- Thymeleaf
- PostgreSQL
- Bootstrap
- Lombok
  
 > Java 11 kullanarak IntelliJ IDEA üzerinde geliştirdim. Projeyi oluşturmak için IntelliJ IDEA üzerinden Spring Inıtializr kullandım. veritabanı sistemi olarak PostgreSQL kullandım.
  
## Kurulum :floppy_disk:

Öncelikle kullandığınız işletim sistemi üzerinde bir terminal açıp projeyi indireceğiniz dosyaya gidin ve daha sonra aşağıdaki kodu çalıştırarak repoyu indiriyoruz.

>Git kodlarını çalıştırabilmek için git'in sisteminizde kurulu olması gereklidir. Kontrol için terminalden git --version yazabilirsiniz

```bash
git clone https://github.com/kagangunduz/vetapp.git
```

Daha sonra projeyi çalışmak istediğiniz IDE (Eclipse, NetBeans, IntelliJ IDEA) ile açarak **pom.xml** dosyasındaki bağımlıklıkları yüklüyoruz.

Ayrıca veritabanı sistemi olarak PostgreSQL kurulumunu yapıyoruz. Daha sonra projemizde kullanmak üzere bir veritabanı oluşturuyoruz.

>Farklı bir veritabanı kullanmak isterseniz (MySQL, MsSQL vb.) application.yml üzerinden gerekli ayarları yapmalısınız.

Proje bağımlılıkları yüklendikten sonra proje içerisindeki **application.yml** üzerinden veritabanı ayarlarını yapıyoruz.

Veritabanı ayarlarını yaptıktan sonra bir sorun yoksa uygulamayı çalıştırıp kullanmaya başlayabiliriz. :clap::clap::clap:


