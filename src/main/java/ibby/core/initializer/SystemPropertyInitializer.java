package ibby.core.initializer;

import org.apache.logging.log4j.LogManager;
import org.testng.ITestContext;

import java.util.Map;

public final class SystemPropertyInitializer {
    private SystemPropertyInitializer() {
    }

    public static void initProperties(ITestContext context) {
        //Идем в enum и спрашиваем какие переменные нам надо
        // установить в системе, если они не установлены
        LogManager.getLogger().debug("Initializing properties...");
        SystemProperties[] values = SystemProperties.values();
        // Идем "в testng.xml" и берем значения переменных по дефолту
        Map<String, String> defaultValues = context.getCurrentXmlTest().getAllParameters();
        // Перебираем по очереди все переменные, которые должны быть
        // установлены в системе
        for (SystemProperties value : values) {
            // Узнаем имя, под которым наша переменная должна храниться в системе
            String propertyName = value.getSystemName();
            // Узнаем в системе значения переменной
            String existingValue = System.getProperty(propertyName);
            if (existingValue == null) {
                // Раз в системе нет переменной, точнее она null,
                // то устанавливаем дефолтовое значение(из testng.xml)
                LogManager.getLogger().warn(String.format("Default value for property %s = %s", value.name(), defaultValues.get(value.name())));
                System.setProperty(propertyName, defaultValues.get(value.name()));
            }
        }
    }
}
