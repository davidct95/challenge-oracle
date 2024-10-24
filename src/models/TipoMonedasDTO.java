package models;

import java.util.Map;

public record TipoMonedasDTO(String result, String base_code, String target_code, Double conversion_rate) {
}
