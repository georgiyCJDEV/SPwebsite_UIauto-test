package org.georgiydev.configuration;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import org.georgiydev.utils.PropertiesParser;

/**
 * Singleton для взаимодействия с конфигурационным файлом и прочими
 * параметрами заданными через ресурс-файл testConfig.properties
 */
public class SelenideSetup {
    // Объект класса SelenideSetup
    private static SelenideSetup instance;
    /**
     * Вызывается метод loadConfiguration()
     * для загрузки конфигураций из файла
     */
    private SelenideSetup()
    {
        loadConfiguration();
    }

    /**
     * Метод загрузки конфигурации, обращение к методам вспомогательного класса PropertiesParser,
     * парсящего файл testConfig.properties в директории resources
     */
    private static void loadConfiguration() {
        // Установление значение статическому полю browser класса Configuration для Selenide
        Configuration.browser = PropertiesParser.parseBrowser();
        // Установление значение статическому полю holdBrowserOpen класса Configuration для Selenide
        Configuration.holdBrowserOpen=PropertiesParser.parseHoldBrowserOpen();
        // Установление значение статическому полю browserSize класса Configuration для Selenide
        Configuration.browserSize=PropertiesParser.parseBrowserSize();
    }

    /**
     Если объект не был инициализирован - вызываем конструктор и возвращаем объект,
     если объект проинициализируем - возвращаем объект
     */
    public static SelenideSetup getInstance()
    {
        return instance = instance == null ? new SelenideSetup() : instance;
    }

    /**
     * Открывает сайт по ссылке заданной в конфигурационном файле
     */
    public void openUrl()
    {
        Selenide.open(PropertiesParser.parseUrl());
    }
}
