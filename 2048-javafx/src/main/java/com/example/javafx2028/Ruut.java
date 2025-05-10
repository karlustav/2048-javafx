package com.example.javafx2028;

// Ruut on objekt, mis aitab paremini loogikat rakendada
// Väljakul pole mitte numbrid, vaid Ruudud
public class Ruut {

    // Ruudu arvuline väärtus (2, 4, jne)
    private int vaartus;

    // Ruudu positsioon väljakul
    private int x;
    private int y;

    // Väljak, kuhu ruut kuulub
    private Valjak valjak;

    // Konstruktor
    public Ruut(int vaartus, int y, int x, Valjak valjak) {
        this.vaartus = vaartus;
        this.x = x;
        this.y = y;
        this.valjak = valjak;
    }

    // Liigutab ruutu vastavalt suunale. Kutsutakse välja Valjak update meetodis
    public void liigu(String suund) {

        // Jätab meelde ruudu vana positsiooni
        int oldX = x;
        int oldY = y;

        // Vaatab läbi kõik suunad
        if (suund.equals("yles")) {

            // Kui üleval on vaba koht
            if (y != 0 && !checkCollision("yles")) {

                // Vähendab y, paneb selle ruudu uuele positsioonile ja muudab vana positsiooni nulliks
                y--;
                valjak.getValjak()[y][x] = this;
                valjak.getValjak()[oldY][oldX] = null;

                // Eemaldame ruudu ka väljaku ruutude listist
                valjak.getRuudud().remove(ruutAsukohaJargi(oldX, oldY));
                return;
            }
            // Kui üleval on ruut ja see on sama väärtusega.
            else if (checkCollision(suund) && ruutAsukohaJargi(this.x, this.y - 1).getVaartus() == this.vaartus) {

                // Muudab selle ruudu väärtust, millega esimene ruut kokku põrkas
                ruutAsukohaJargi(this.x, this.y - 1).setVaartus(this.vaartus * 2);

                // Uuendab skoori
                Mang.skoor += this.vaartus * 2;

                // Kontrollib, kas tekkis 2048 ja pole juba võitu tulnud
                if (!Mang.voit && Mang.skoor >= 2048) {
                    System.out.println("VÕITSID! Aga võid jätkata ikka");
                    Mang.voit = true;
                }

                // Eemaldab vana ruudu väljakult ja ruutude listist
                valjak.getValjak()[this.y][this.x] = null;
                valjak.getRuudud().remove(this);
                return;
            }
        }

        // Analoogselt teiste suundadega
        else if (suund.equals("alla")) {
            if (y != 3 && !checkCollision("alla")) {
                y++;
                valjak.getValjak()[y][x] = this;
                valjak.getValjak()[oldY][oldX] = null;
                valjak.getRuudud().remove(ruutAsukohaJargi(oldX, oldY));
                return;
            } else if (checkCollision(suund) && ruutAsukohaJargi(this.x, this.y + 1).getVaartus() == this.vaartus) {
                ruutAsukohaJargi(this.x, this.y + 1).setVaartus(this.vaartus * 2);
                Mang.skoor += this.vaartus * 2;
                if (!Mang.voit && Mang.skoor >= 2048) {
                    System.out.println("VÕITSID! Aga võid jätkata ikka");
                    Mang.voit = true;
                }
                valjak.getValjak()[this.y][this.x] = null;
                valjak.getRuudud().remove(this);
                return;
            }
        }

        else if (suund.equals("paremale")) {
            if (x != 3 && !checkCollision("paremale")) {
                x++;
                valjak.getValjak()[y][x] = this;
                valjak.getValjak()[oldY][oldX] = null;
                valjak.getRuudud().remove(ruutAsukohaJargi(oldX, oldY));
                return;
            } else if (checkCollision(suund) && ruutAsukohaJargi(this.x + 1, this.y).getVaartus() == this.vaartus) {
                ruutAsukohaJargi(this.x + 1, this.y).setVaartus(this.vaartus * 2);
                Mang.skoor += this.vaartus * 2;
                if (!Mang.voit && Mang.skoor >= 2048) {
                    System.out.println("VÕITSID! Aga võid jätkata ikka");
                    Mang.voit = true;
                }
                valjak.getValjak()[this.y][this.x] = null;
                valjak.getRuudud().remove(this);
                return;
            }
        }

        else if (suund.equals("vasakule")) {
            if (x != 0 && !checkCollision("vasakule")) {
                x--;
                valjak.getValjak()[y][x] = this;
                valjak.getValjak()[oldY][oldX] = null;
                valjak.getRuudud().remove(ruutAsukohaJargi(oldX, oldY));
                return;
            } else if (checkCollision(suund) && ruutAsukohaJargi(this.x - 1, this.y).getVaartus() == this.vaartus) {
                ruutAsukohaJargi(this.x - 1, this.y).setVaartus(this.vaartus * 2);
                Mang.skoor += this.vaartus * 2;
                if (!Mang.voit && Mang.skoor >= 2048) {
                    System.out.println("VÕITSID! Aga võid jätkata ikka");
                    Mang.voit = true;
                }
                valjak.getValjak()[this.y][this.x] = null;
                valjak.getRuudud().remove(this);
                return;
            }
        }
    }

    // Liigutab ruutu vastavalt suunale. Kutsutakse välja Valjak update meetodis
    public void liiguTest(String suund) {

        // Jätab meelde ruudu vana positsiooni
        int oldX = x;
        int oldY = y;

        // Vaatab läbi kõik suunad
        if (suund.equals("yles")) {

            // Kui üleval on vaba koht
            if (y != 0 && !checkCollision("yles")) {

                // Vähendab y, paneb selle ruudu uuele positsioonile ja muudab vana positsiooni nulliks
                y--;
                valjak.getValjak()[y][x] = this;
                valjak.getValjak()[oldY][oldX] = null;

                // Eemaldame ruudu ka väljaku ruutude listist
                valjak.getRuudud().remove(ruutAsukohaJargi(oldX, oldY));
                return;
            }
            // Kui üleval on ruut ja see on sama väärtusega.
            else if (checkCollision(suund) && ruutAsukohaJargi(this.x, this.y - 1).getVaartus() == this.vaartus) {

                // Muudab selle ruudu väärtust, millega esimene ruut kokku põrkas
                ruutAsukohaJargi(this.x, this.y - 1).setVaartus(this.vaartus * 2);

                // Eemaldab vana ruudu väljakult ja ruutude listist
                valjak.getValjak()[this.y][this.x] = null;
                valjak.getRuudud().remove(this);
                return;
            }
        }

        // Analoogselt teiste suundadega
        else if (suund.equals("alla")) {
            if (y != 3 && !checkCollision("alla")) {
                y++;
                valjak.getValjak()[y][x] = this;
                valjak.getValjak()[oldY][oldX] = null;
                valjak.getRuudud().remove(ruutAsukohaJargi(oldX, oldY));
                return;
            } else if (checkCollision(suund) && ruutAsukohaJargi(this.x, this.y + 1).getVaartus() == this.vaartus) {
                ruutAsukohaJargi(this.x, this.y + 1).setVaartus(this.vaartus * 2);

                valjak.getValjak()[this.y][this.x] = null;
                valjak.getRuudud().remove(this);
                return;
            }
        }

        else if (suund.equals("paremale")) {
            if (x != 3 && !checkCollision("paremale")) {
                x++;
                valjak.getValjak()[y][x] = this;
                valjak.getValjak()[oldY][oldX] = null;
                valjak.getRuudud().remove(ruutAsukohaJargi(oldX, oldY));
                return;
            } else if (checkCollision(suund) && ruutAsukohaJargi(this.x + 1, this.y).getVaartus() == this.vaartus) {
                ruutAsukohaJargi(this.x + 1, this.y).setVaartus(this.vaartus * 2);

                valjak.getValjak()[this.y][this.x] = null;
                valjak.getRuudud().remove(this);
                return;
            }
        }

        else if (suund.equals("vasakule")) {
            if (x != 0 && !checkCollision("vasakule")) {
                x--;
                valjak.getValjak()[y][x] = this;
                valjak.getValjak()[oldY][oldX] = null;
                valjak.getRuudud().remove(ruutAsukohaJargi(oldX, oldY));
                return;
            } else if (checkCollision(suund) && ruutAsukohaJargi(this.x - 1, this.y).getVaartus() == this.vaartus) {
                ruutAsukohaJargi(this.x - 1, this.y).setVaartus(this.vaartus * 2);

                valjak.getValjak()[this.y][this.x] = null;
                valjak.getRuudud().remove(this);
                return;
            }
        }
    }






    // Võtab parameetriteks x ja y ning tagastab selle ruudu, mis väljakul on sellel positsioonil
    public Ruut ruutAsukohaJargi(int x, int y) {
        for (Ruut ruut: valjak.getRuudud()) {
            if (ruut.getX() == x && ruut.getY() == y) {
                return ruut;
            }
        }
        return null;
    }

    // Vaatab, kas seal on juba mingi teine ruut, kuhu algne ruut tahab liikuda
    public boolean checkCollision(String suund) {
        boolean onCollision = false;
        for (Ruut teineRuut: valjak.getRuudud()) {
            if (suund.equals("yles")) {
                onCollision = teineRuut.getY() == this.y - 1 && teineRuut.getX() == this.x;
            }
            else if (suund.equals("alla")) {
                onCollision = teineRuut.getY() == this.y + 1 && teineRuut.getX() == this.x;
            }
            else if (suund.equals("paremale")) {
                onCollision = teineRuut.getX() == this.x + 1 && teineRuut.getY() == this.y;
            }
            else if (suund.equals("vasakule")) {
                onCollision =  teineRuut.getX() == this.x - 1 && teineRuut.getY() == this.y;
            }
            if (onCollision) {
                return true;
            }
        }
        return false;

    }

    // Getterid/Setterid
    public Valjak getValjak() {
        return valjak;
    }
    public void setValjak(Valjak valjak) {
        this.valjak = valjak;
    }
    public int getVaartus() {
        return vaartus;
    }
    public void setVaartus(int vaartus) {
        this.vaartus = vaartus;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    //toString. Tagastab väärtuse String kujul.
    public String toString() {
        return vaartus + "";
    }




}

