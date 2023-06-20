package itmasters.project.stem.translator;

import lombok.Data;

import java.util.List;

@Data
public class TranslatorResponse {
    int code;
    String lang;
    List<String> text;
}
