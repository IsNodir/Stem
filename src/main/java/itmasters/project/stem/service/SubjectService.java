package itmasters.project.stem.service;

import com.google.gson.Gson;
import itmasters.project.stem.entity.Subject;
import itmasters.project.stem.payload.subject.SubjectDTO;
import itmasters.project.stem.payload.subject.SubjectEn;
import itmasters.project.stem.payload.subject.SubjectUz;
import itmasters.project.stem.repository.SubjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAllSubject() {
        return subjectRepository.findAll();
    }

    public Object getAllSubjectByLanguage1(String language) throws JSONException {

        List<Subject> subjectList = subjectRepository.findAll();

        JSONArray jsonArray = new JSONArray(subjectList);

        System.out.println("jsonArray = " + jsonArray);


        List<Object> uzSubjectList = new ArrayList<>();
        List<Object> enSubjectList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject subjectObject = jsonArray.getJSONObject(i);
            int id = subjectObject.getInt("id");
            String subjectNameUz = subjectObject.getString("subjectNameUz");
            double price = subjectObject.getDouble("price");
            int streak = subjectObject.getInt("streak");
            int coins = subjectObject.getInt("coins");
            String streakFirstDay = subjectObject.getString("streakFirstDay");

            JSONArray topics = subjectObject.getJSONArray("topics");
            List<Object> uzTopicList = new ArrayList<>();

            for (int j = 0; j < topics.length(); j++) {
                JSONObject topicObject = topics.getJSONObject(j);
                int topicId = topicObject.getInt("id");
                String topicNameUz = topicObject.getString("topicNameUz");
                String topicCoins = topicObject.getString("coins");

                // Create topic object
                JSONObject uzTopic = new JSONObject();
                uzTopic.put("id", topicId);
                uzTopic.put("topicNameUz", topicNameUz);
                uzTopic.put("coins", topicCoins);
                uzTopic.put("quiz", new ArrayList<>());

                uzTopicList.add(uzTopic);
            }

            // Create subject object
            JSONObject uzSubject = new JSONObject();
            uzSubject.put("id", id);
            uzSubject.put("subjectNameUz", subjectNameUz);
            uzSubject.put("price", price);
            uzSubject.put("streak", streak);
            uzSubject.put("coins", coins);
            uzSubject.put("streakFirstDay", streakFirstDay);
            uzSubject.put("topics", uzTopicList);

            uzSubjectList.add(uzSubject);
        }

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject subjectObject = jsonArray.getJSONObject(i);
            int id = subjectObject.getInt("id");
            String subjectNameEn = subjectObject.getString("subjectNameEn");
            double price = subjectObject.getDouble("price");
            int streak = subjectObject.getInt("streak");
            int coins = subjectObject.getInt("coins");
            String streakFirstDay = subjectObject.getString("streakFirstDay");

            JSONArray topics = subjectObject.getJSONArray("topics");
            List<Object> enTopicList = new ArrayList<>();

            for (int j = 0; j < topics.length(); j++) {
                JSONObject topicObject = topics.getJSONObject(j);
                int topicId = topicObject.getInt("id");
                String topicNameEn = topicObject.getString("topicNameEn");
                String topicCoins = topicObject.getString("coins");

                // Create topic object
                JSONObject enTopic = new JSONObject();
                enTopic.put("id", topicId);
                enTopic.put("topicNameEn", topicNameEn);
                enTopic.put("coins", topicCoins);
                enTopic.put("quiz", new ArrayList<>());

                enTopicList.add(enTopic);
            }

            // Create subject object
            JSONObject enSubject = new JSONObject();
            enSubject.put("id", id);
            enSubject.put("subjectNameEn", subjectNameEn);
            enSubject.put("price", price);
            enSubject.put("streak", streak);
            enSubject.put("coins", coins);
            enSubject.put("streakFirstDay", streakFirstDay);
            enSubject.put("topics", enTopicList);

            enSubjectList.add(enSubject);
        }

        return language.equals("uz") ? uzSubjectList : language.equals("en") ? enSubjectList : null;
    }



    public Object getAllSubjectByLanguage(String language) throws JSONException {
        List<Subject> subjectList = subjectRepository.findAll();

        Gson gson = new Gson();
            String json1 = gson.toJson(subjectList.get(0));
        System.out.println("json1 = " + json1);
        String json3 = gson.toJson(subjectList.get(3));
        System.out.println("json3 = " + json3);
        String json5 = gson.toJson(subjectList.get(1));
        System.out.println("json5 = " + json5);
        String json4 = gson.toJson(subjectList.get(4));
        System.out.println("json4 = " + json4);
        String json2 = gson.toJson(subjectList.get(2));
        System.out.println("json2 = " + json2);


        for (Subject subject : subjectList) {
            String json = gson.toJson(subject);
            System.out.println("json = " + json);
        }

        for (int i = 0; i < subjectList.size(); i++) {
            JSONObject jsonObject = new JSONObject(subjectList.get(i).toString());
            System.out.println("jsonObject = " + jsonObject);
        }
        List<SubjectUz> uzSubjectList = new ArrayList<>();
        List<SubjectEn> enSubjectList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < jsonObject.length(); i++) {
            JSONObject subjectObject = jsonObject.getJSONObject("i");
            int id = subjectObject.getInt("id");
            String subjectNameUz = subjectObject.getString("subjectNameUz");
            double price = subjectObject.getDouble("price");
            int streak = subjectObject.getInt("streak");
            int coins = subjectObject.getInt("coins");
            String streakFirstDay = subjectObject.getString("streakFirstDay");

            SubjectUz subjectUz = new SubjectUz();
            subjectUz.setId(id);
            subjectUz.setSubjectNameUz(subjectNameUz);
            subjectUz.setPrice(price);
            subjectUz.setStreak(streak);
            subjectUz.setCoins(coins);
            subjectUz.setStreakFirstDay(Date.valueOf(streakFirstDay));

//            jsonArray.optJSONObject(i).get("id");
//            jsonArray.optJSONObject(i).get("subjectNameUz");
//            jsonArray.optJSONObject(i).get("price");
//            jsonArray.optJSONObject(i).get("streak");
//            jsonArray.optJSONObject(i).get("coins");
//            jsonArray.optJSONObject(i).get("streakFirstDay");
//            JSONArray topics = jsonArray.getJSONObject(i).optJSONArray("topics");
//
//            topics.getJSONObject(i).get("id");
//            topics.getJSONObject(i).get("topicNameUz");
//            topics.getJSONObject(i).get("coins");
            uzSubjectList.add(subjectUz);
        }

//        for (int i = 0; i < jsonArray.length(); i++) {
//            jsonArray.getJSONObject(i).get("id");
//            jsonArray.getJSONObject(i).get("subjectNameEn");
//            jsonArray.getJSONObject(i).get("price");
//            jsonArray.getJSONObject(i).get("streak");
//            jsonArray.getJSONObject(i).get("coins");
//            jsonArray.getJSONObject(i).get("streakFirstDay");
//
//            jsonArray.getJSONObject(i).getJSONArray("topics").getJSONObject(i).get("id");
//            jsonArray.getJSONObject(i).getJSONArray("topics").getJSONObject(i).get("topicNameEn");
//            jsonArray.getJSONObject(i).getJSONArray("topics").getJSONObject(i).get("coins");
//            enSubjectList.add(jsonArray);
//        }
        return language.equals("uz") ? uzSubjectList : language.equals("en") ? enSubjectList : null;
    }

    public Subject getSubjectById(Integer subjectId) {
        return subjectRepository.findById(subjectId).orElseThrow();
    }

    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject updateSubject(Integer subjectId, SubjectDTO subjectDTO) {
        Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
        if (optionalSubject.isEmpty()) {
            throw new RuntimeException();
        }
        Subject updatedSubject = optionalSubject.get();
        updatedSubject.setSubjectNameUz(subjectDTO.getSubjectNameUz());
        updatedSubject.setSubjectNameEn(subjectDTO.getSubjectNameEn());
        updatedSubject.setPrice(subjectDTO.getPrice());
        updatedSubject.setStreak(subjectDTO.getStreak());
        updatedSubject.setStreakFirstDay(subjectDTO.getStreakFirstDay());
        updatedSubject.setCoins(subjectDTO.getCoins());
        return subjectRepository.save(updatedSubject);
    }

    public String deleteSubject(Integer subjectId) {
        Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
        if (optionalSubject.isEmpty()) {
            throw new RuntimeException();
        }
        subjectRepository.deleteById(optionalSubject.get().getId());
        return "Subject deleted successfully";
    }

}
