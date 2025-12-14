package com.koasleep.core.config;

import com.koasleep.core.model.*;
import com.koasleep.core.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;

//@Component // Comment this out to prevent this file from running
@Profile("dev")
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;

    public DatabaseSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        System.out.println("🌱 Starting seed process...");

        userRepository.deleteAll();
        System.out.println("🗑️  Deleted all existing users and their related data.");

        User user = new User();
        user.setEmail("demo@koa");
        user.setFullName("Koa Sleep");
        user.setDisplayName("KoaSleep");
        user.setFirstName("Koa");
        user.setLastName("Sleep");

        System.out.println("📊 Seeding biometric and sleep data for 14 days...");

        seedSleepLogs(user);
        seedSkinTemperatures(user);
        seedBreathingRates(user);
        seedHeartRateVariability(user);
        seedSpo2Readings(user);
        seedSmartSummaries(user);

        userRepository.save(user);

        System.out.println("👤 Created user '" + user.getFirstName() + "' with id: " + user.getId());
        System.out.println("✅ Seeding finished successfully!");
    }

    // --- HELPER METHODS TO POPULATE DATA ---

    private void seedSleepLogs(User user) {
        user.addSleepLog(createLog("2025-08-18", "2025-08-17T23:50:00Z", "2025-08-18T06:30:00Z", 24000000L, 86, 65, 220, 55, 90));
        user.addSleepLog(createLog("2025-08-19", "2025-08-18T22:30:00Z", "2025-08-19T06:45:00Z", 29700000L, 95, 30, 270, 85, 110));
        user.addSleepLog(createLog("2025-08-20", "2025-08-19T22:45:00Z", "2025-08-20T07:00:00Z", 29700000L, 94, 35, 265, 90, 105));
        user.addSleepLog(createLog("2025-08-21", "2025-08-20T23:30:00Z", "2025-08-21T06:15:00Z", 24300000L, 88, 55, 215, 60, 95));
        user.addSleepLog(createLog("2025-08-22", "2025-08-21T22:55:00Z", "2025-08-22T06:55:00Z", 28800000L, 96, 28, 255, 80, 117));
        user.addSleepLog(createLog("2025-08-23", "2025-08-22T23:00:00Z", "2025-08-23T07:10:00Z", 29400000L, 93, 39, 260, 78, 113));
        user.addSleepLog(createLog("2025-08-24", "2025-08-23T23:10:00Z", "2025-08-24T06:00:00Z", 24600000L, 85, 62, 225, 58, 85));
        user.addSleepLog(createLog("2025-08-25", "2025-08-24T22:50:00Z", "2025-08-25T06:20:00Z", 27000000L, 90, 50, 230, 80, 90));
        user.addSleepLog(createLog("2025-08-26", "2025-08-25T23:00:00Z", "2025-08-26T07:00:00Z", 28800000L, 94, 30, 260, 75, 115));
        user.addSleepLog(createLog("2025-08-27", "2025-08-26T22:30:00Z", "2025-08-27T06:15:00Z", 27900000L, 96, 25, 250, 70, 120));
        user.addSleepLog(createLog("2025-08-28", "2025-08-27T23:15:00Z", "2025-08-28T07:00:00Z", 27900000L, 88, 58, 245, 60, 102));
        user.addSleepLog(createLog("2025-08-29", "2025-08-28T22:55:00Z", "2025-08-29T06:45:00Z", 28200000L, 93, 40, 240, 95, 95));
        user.addSleepLog(createLog("2025-08-30", "2025-08-29T23:10:00Z", "2025-08-30T06:00:00Z", 24600000L, 85, 62, 225, 58, 85));
        user.addSleepLog(createLog("2025-08-31", "2025-08-30T22:45:00Z", "2025-08-31T06:30:00Z", 27900000L, 92, 45, 250, 65, 105));
    }

    private SleepLog createLog(String date, String bed, String wake, long dur, int eff, int awake, int light, int deep, int rem) {
        SleepLog log = new SleepLog();
        log.setDate(LocalDate.parse(date));
        log.setBedTime(OffsetDateTime.parse(bed));
        log.setWakeTime(OffsetDateTime.parse(wake));
        log.setDurationMs(dur);
        log.setEfficiency(eff);
        log.setAwakeMins(awake);
        log.setLightMins(light);
        log.setDeepMins(deep);
        log.setRemMins(rem);
        return log;
    }

    private void seedSkinTemperatures(User user) {
        user.addSkinTemperature(createTemp("2025-08-18", 1.0));
        user.addSkinTemperature(createTemp("2025-08-19", 0.2));
        user.addSkinTemperature(createTemp("2025-08-20", -0.1));
        user.addSkinTemperature(createTemp("2025-08-21", -1.1));
        user.addSkinTemperature(createTemp("2025-08-22", 0.4));
        user.addSkinTemperature(createTemp("2025-08-23", 0.1));
        user.addSkinTemperature(createTemp("2025-08-24", 0.9));
        user.addSkinTemperature(createTemp("2025-08-25", 0.2));
        user.addSkinTemperature(createTemp("2025-08-26", -0.5));
        user.addSkinTemperature(createTemp("2025-08-27", 0.0));
        user.addSkinTemperature(createTemp("2025-08-28", -1.2));
        user.addSkinTemperature(createTemp("2025-08-29", 0.3));
        user.addSkinTemperature(createTemp("2025-08-30", 0.9));
        user.addSkinTemperature(createTemp("2025-08-31", -0.4));
    }

    private SkinTemperature createTemp(String date, double avg) {
        SkinTemperature st = new SkinTemperature();
        st.setDate(LocalDate.parse(date));
        st.setAverage(avg);
        return st;
    }

    private void seedBreathingRates(User user) {
        user.addBreathingRate(createBreath("2025-08-18", 16.4));
        user.addBreathingRate(createBreath("2025-08-19", 15.5));
        user.addBreathingRate(createBreath("2025-08-20", 15.3));
        user.addBreathingRate(createBreath("2025-08-21", 16.2));
        user.addBreathingRate(createBreath("2025-08-22", 15.1));
        user.addBreathingRate(createBreath("2025-08-23", 15.4));
        user.addBreathingRate(createBreath("2025-08-24", 16.5));
        user.addBreathingRate(createBreath("2025-08-25", 16.3));
        user.addBreathingRate(createBreath("2025-08-26", 15.6));
        user.addBreathingRate(createBreath("2025-08-27", 15.4));
        user.addBreathingRate(createBreath("2025-08-28", 16.4));
        user.addBreathingRate(createBreath("2025-08-29", 16.1));
        user.addBreathingRate(createBreath("2025-08-30", 16.5));
        user.addBreathingRate(createBreath("2025-08-31", 15.7));
    }

    private BreathingRate createBreath(String date, double rate) {
        BreathingRate br = new BreathingRate();
        br.setDate(LocalDate.parse(date));
        br.setBreathingRate(rate);
        return br;
    }

    private void seedHeartRateVariability(User user) {
        user.addHeartRateVariability(createHRV("2025-08-18", 43.1, 49.5));
        user.addHeartRateVariability(createHRV("2025-08-19", 58.9, 66.2));
        user.addHeartRateVariability(createHRV("2025-08-20", 59.5, 67.0));
        user.addHeartRateVariability(createHRV("2025-08-21", 44.5, 50.1));
        user.addHeartRateVariability(createHRV("2025-08-22", 61.2, 69.0));
        user.addHeartRateVariability(createHRV("2025-08-23", 57.3, 64.8));
        user.addHeartRateVariability(createHRV("2025-08-24", 42.0, 48.8));
        user.addHeartRateVariability(createHRV("2025-08-25", 53.4, 60.1));
        user.addHeartRateVariability(createHRV("2025-08-26", 58.2, 65.0));
        user.addHeartRateVariability(createHRV("2025-08-27", 60.1, 68.3));
        user.addHeartRateVariability(createHRV("2025-08-28", 41.5, 48.2));
        user.addHeartRateVariability(createHRV("2025-08-29", 51.0, 58.5));
        user.addHeartRateVariability(createHRV("2025-08-30", 42.0, 48.8));
        user.addHeartRateVariability(createHRV("2025-08-31", 48.2, 55.9));
    }

    private HeartRateVariability createHRV(String date, double daily, double deep) {
        HeartRateVariability hrv = new HeartRateVariability();
        hrv.setDate(LocalDate.parse(date));
        hrv.setDailyRmssd(daily);
        hrv.setDeepRmssd(deep);
        return hrv;
    }

    private void seedSpo2Readings(User user) {
        user.addSpo2Reading(createSpo2("2025-08-18", 94, 90, 98));
        user.addSpo2Reading(createSpo2("2025-08-19", 97, 94, 100));
        user.addSpo2Reading(createSpo2("2025-08-20", 98, 95, 100));
        user.addSpo2Reading(createSpo2("2025-08-21", 95, 91, 99));
        user.addSpo2Reading(createSpo2("2025-08-22", 97, 94, 99));
        user.addSpo2Reading(createSpo2("2025-08-23", 96, 93, 100));
        user.addSpo2Reading(createSpo2("2025-08-24", 94, 90, 97));
        user.addSpo2Reading(createSpo2("2025-08-25", 97, 95, 100));
        user.addSpo2Reading(createSpo2("2025-08-26", 96, 93, 99));
        user.addSpo2Reading(createSpo2("2025-08-27", 97, 94, 100));
        user.addSpo2Reading(createSpo2("2025-08-28", 95, 91, 98));
        user.addSpo2Reading(createSpo2("2025-08-29", 96, 92, 99));
        user.addSpo2Reading(createSpo2("2025-08-30", 94, 90, 97));
        user.addSpo2Reading(createSpo2("2025-08-31", 96, 93, 99));
    }

    private Spo2Reading createSpo2(String date, int avg, int min, int max) {
        Spo2Reading spo2 = new Spo2Reading();
        spo2.setDate(LocalDate.parse(date));
        spo2.setAvg(avg);
        spo2.setMin(min);
        spo2.setMax(max);
        return spo2;
    }

    private void seedSmartSummaries(User user) {
        user.addSmartSummary(createSummary("2025-08-27",
                "You had an excellent night of restorative sleep. Your total time of 7 hours and 45 minutes combined with an outstanding 96% efficiency score indicates very high-quality rest. You achieved healthy amounts of both deep and REM sleep, which are vital for physical repair and mental clarity. Your vital signs, including a strong Heart Rate Variability (HRV) and stable breathing rate, suggest your nervous system recovered well overnight. All other health metrics appear stable and within a healthy range."));
        user.addSmartSummary(createSummary("2025-08-28",
                "You had a decent night of rest, though your sleep efficiency of 88% was impacted by nearly an hour of awake time. On the positive side, you achieved healthy amounts of both deep and REM sleep, which are essential for physical repair and cognitive function. A notable highlight is the significant drop in your skin temperature; this cooling is a key physiological trigger for high-quality, restorative sleep. Your breathing rate, HRV, and SpO2 levels all appear stable."));
        user.addSmartSummary(createSummary("2025-08-29",
                "You had an excellent night's rest. Your total sleep duration of 7 hours and 50 minutes, combined with a high efficiency score of 93%, indicates very restorative sleep. You spent a significant amount of time in both deep and REM sleep, which are crucial for physical recovery and mental processing. Your health metrics all appear stable. The slight rise in your skin temperature is not a concern given your other strong results, but be aware that a cooler environment can sometimes enhance sleep quality even further."));
        user.addSmartSummary(createSummary("2025-08-30",
                "Your sleep last night was a bit restless. While you achieved respectable amounts of deep and REM sleep, your overall efficiency was lowered by over an hour of awake time. A key insight is your elevated skin temperature; this increase can sometimes interfere with sleep quality and might suggest your sleeping environment was too warm. Your other metrics, including your blood oxygen and HRV, fall within a range that suggests your body was managing okay despite the restlessness."));
        user.addSmartSummary(createSummary("2025-08-31",
                "You achieved a highly restorative night's rest. Your total sleep time of 7 hours and 45 minutes, paired with an excellent 92% efficiency score, indicates very effective sleep. You spent healthy amounts of time in both deep and REM sleep, which are crucial for physical recovery and memory consolidation. Notably, the slight drop in your skin temperature is a positive sign; this cooling process is a key physiological trigger for deep, quality sleep. Your other health metrics all appear stable and healthy."));
    }

    private SmartSummary createSummary(String date, String text) {
        SmartSummary ss = new SmartSummary();
        ss.setDate(LocalDate.parse(date));
        ss.setSummary(text);
        return ss;
    }
}