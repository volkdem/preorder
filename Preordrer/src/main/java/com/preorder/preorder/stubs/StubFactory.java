package com.preorder.preorder.stubs;

import org.prototype.model.Product;
import org.prototype.model.ProductsCatalog;
import org.prototype.model.Restraint;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Evgeny on 29.11.2015.
 */
public class StubFactory {

    public static List<Restraint> getRestraints() {
        final String BURGER_KING = "Бургер Кинг";
        return Arrays.asList( new Restraint[]{
            new Restraint(1L, BURGER_KING, "Москва, ул. Сущевский Вал, 31, стр. 1"),
            new Restraint(2L, BURGER_KING, "Москва, ул. Шереметьевская, 20, ТЦ Ашан Капитолий")
        });
    }

    public static List< ProductsCatalog > getProductCatalogs(long restrantId) {
        List<ProductsCatalog> catalogs = new ArrayList<ProductsCatalog>();
        ProductsCatalog catalog = new ProductsCatalog(1L, "Бургеры");
        catalogs.add(catalog);
        catalog.addProduct(new Product(1L, "ВОППЕР", new BigDecimal(10.0)));
        catalog.addProduct(new Product(2L, "ВОППЕР ДЖУНИОР", new BigDecimal(20.0)));
        catalog.addProduct(new Product(3L, "ДВОЙНОЙ ВОППЕР", new BigDecimal(30.0)));
        catalog.addProduct(new Product(4L, "ТРОЙНОЙ ВОППЕР", new BigDecimal(40.0)));
        catalog.addProduct(new Product(5L, "СТЕЙКХАУС", new BigDecimal(50.0)));
        catalog.addProduct(new Product(6L, "ДВОЙНОЙ ЧИЗБУРГЕР", new BigDecimal(60.0)));
        catalog.addProduct(new Product(7L, "ЧИЗБУРГЕР", new BigDecimal(70.0)));
        catalog.addProduct(new Product(8L, "ГАМБУРГЕР", new BigDecimal(80.0)));
        catalog.addProduct(new Product(10L, "ГРИЛЬ ЧИКЕН БАРБЕКЮ", new BigDecimal(90.0)));
        catalog.addProduct(new Product(11L, "ТЕНДЕРКРИСП", new BigDecimal(100.0)));
        catalog.addProduct(new Product(12L, "ЛОНГ ЧИКЕН", new BigDecimal(110.0)));
        catalog.addProduct(new Product(13L, "КРИСПИ ЧИКЕН", new BigDecimal(120.0)));
        catalog.addProduct(new Product(14L, "ЧИКЕНБУРГЕР", new BigDecimal(130.0)));
        catalog.addProduct(new Product(15L, "ФИШ КИНГ", new BigDecimal(140.0)));
        catalog.addProduct(new Product(16L, "ЦЕЗАРЬ РОЛЛ", new BigDecimal(150.0)));
        catalog.addProduct(new Product(17L, "БАРБЕКЮ РИФФ РОЛЛ", new BigDecimal(160.0)));

        catalog = new ProductsCatalog(2L, "Ганиры");
        catalogs.add(catalog);
        catalog.addProduct(new Product(20L, "КАРТОФЕЛЬ ДЕРЕВЕНСКИЙ", new BigDecimal(10.0)));
        catalog.addProduct(new Product(21L, "КАРТОФЕЛЬ КИНГ ФРИ", new BigDecimal(20.0)));
        catalog.addProduct(new Product(22L, "ЛУКОВЫЕ КОЛЕЧКИ", new BigDecimal(30.0)));

        catalog = new ProductsCatalog(3L, "Снеки");
        catalogs.add(catalog);
        catalog.addProduct(new Product(30L, "КРЫЛКИ КИНГ", new BigDecimal(10.0)));
        catalog.addProduct(new Product(31L, "КИНГ НАГГЕТС", new BigDecimal(20.0)));
        catalog.addProduct(new Product(32L, "КИНГ БУКЕТ", new BigDecimal(30.0)));

        catalog = new ProductsCatalog(4L, "Салаты");
        catalogs.add(catalog);
        catalog.addProduct(new Product(40L, "САЛАТ МИКС", new BigDecimal(10.0)));
        catalog.addProduct(new Product(41L, "САЛАТ ЦЕЗАРЬ", new BigDecimal(20.0)));

        catalog = new ProductsCatalog(5L, "Напитки");
        catalogs.add(catalog);
        catalog.addProduct(new Product(50L, "МИНЕРАЛЬНАЯ ВОДА", new BigDecimal(10.0)));
        catalog.addProduct(new Product(51L, "Pepsi/Mirinda/7UP", new BigDecimal(20.0)));

        catalog = new ProductsCatalog(6L, "Десерты");
        catalogs.add(catalog);
        catalog.addProduct(new Product(60L, "Мороженное РОЖОК", new BigDecimal(10.0)));
        catalog.addProduct(new Product(61L, "Горячий БРАУНИ", new BigDecimal(20.0)));
        catalog.addProduct(new Product(62L, "МОРОЖЕННОЕ ТВИКС", new BigDecimal(30.0)));

        return catalogs;
    }
}
