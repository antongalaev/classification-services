package com.galaev.classification.controller;

import com.galaev.classification.model.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class handles client requests
 * and returns responses with JSON objects.
 *
 * @author Anton Galaev
 */
public class StimaController {

    @RequestMapping(value = "/{name}/title", method = RequestMethod.GET, produces = "text/plain")
    public @ResponseBody String getTitle(@PathVariable String name) {
        switch (name) {
            case "quint":
                return "Qualitative Interaction Trees";
            case "stima":
                return "Simultaneous Threshold Interaction Modeling Algorithm";
            default:
                return "No such method implemented here";
        }
    }

    @RequestMapping(value = "/{name}/comment", method = RequestMethod.GET)
    public ModelAndView getComment(@PathVariable String name) {
        ModelAndView model = new ModelAndView("comment");
        switch (name) {
            case "quint":
                model.addObject("title", "Qualitative Interaction Trees (QUINT)");
                model.addObject("content", "Quint is a subgroup analysis tool, " +
                        "suitable for data from a two-arm randomized controlled trial. " +
                        "Grows a qualitative interaction tree. ");
                break;
            case "stima":
                model.addObject("title", "Simultaneous Threshold Interaction Modeling Algorithm (STIMA)");
                model.addObject("content", "STIMA is a classification tool that estimates a regression trunk model," +
                        " which is an integration of a regression tree and a multiple regression model");
                break;
            default:
                model.addObject("title", "No such method implemented here");
                model.addObject("content", "");
        }
        return model;
    }

    @RequestMapping(value = "/{name}/parameters", method = RequestMethod.GET)
    public @ResponseBody ParamsGroups getParameters(@PathVariable String name) {
        ParamsGroups paramsGroups = new ParamsGroups();
        switch (name) {
            case "quint":
                ParamsGroup commonGroup = new ParamsGroup();
                commonGroup.setGroup("Common");
                commonGroup.setCaption("Общие");
                commonGroup.addParameter(new ParameterBuilder()
                        .setParamName("formula")
                        .setParamCaption("Описание модели")
                        .setParamType(ParameterType.STRING)
                        .setParamHint("Описание модели в виде Y ~ T | X1 + ... + XJ, " +
                                "где Y - зависимая переменная, T - переменная типа лечения, " +
                                "Xi - остальные переменные")
                        .setParamDefaultValue("")
                        .setParamInfo("")
                        .build());
                commonGroup.addParameter(new ParameterBuilder()
                        .setParamName("crit")
                        .setParamCaption("Тип критерия")
                        .setParamType(ParameterType.STRING)
                        .setParamHint("Тип критерия разделения. Либо 'dm' - разница в средней эффективности лечения, " +
                                "либо 'es' - разница в размерах эффекта.")
                        .setParamDefaultValue("dm")
                        .setParamInfo("")
                        .build());
                commonGroup.addParameter(new ParameterBuilder()
                        .setParamName("maxl")
                        .setParamCaption("Макс. кол-во подгрупп")
                        .setParamType(ParameterType.INT)
                        .setParamHint("Максимальное количество листьев в финальном дереве.")
                        .setParamDefaultValue("10")
                        .setParamInfo("")
                        .build());
                commonGroup.addParameter(new ParameterBuilder()
                        .setParamName("dmin")
                        .setParamCaption("Минимальная разница для 1 разделения")
                        .setParamType(ParameterType.DOUBLE)
                        .setParamHint("Минимально допустимая разница между эффективностью лечения в " +
                                "подгруппах, полученных при первом разделении")
                        .setParamDefaultValue("0.3")
                        .setParamInfo("")
                        .build());
                commonGroup.addParameter(new ParameterBuilder()
                        .setParamName("a1")
                        .setParamCaption("Мин. кол-во пациентов с лечением А")
                        .setParamType(ParameterType.INT)
                        .setParamHint("Минимальное количество пациентов в одной подгруппе, получавших лечение А.")
                        .setParamDefaultValue("5")
                        .setParamInfo("")
                        .build());
                commonGroup.addParameter(new ParameterBuilder()
                        .setParamName("a2")
                        .setParamCaption("Мин. кол-во пациентов с лечением Б")
                        .setParamType(ParameterType.INT)
                        .setParamHint("Минимальное количество пациентов в одной подгруппе, получавших лечение Б.")
                        .setParamDefaultValue("5")
                        .setParamInfo("")
                        .build());
                commonGroup.addParameter(new ParameterBuilder()
                        .setParamName("w1")
                        .setParamCaption("Вес критерия эффективности лечения")
                        .setParamType(ParameterType.DOUBLE)
                        .setParamHint("В формуле общего критерия разделения - " +
                                "вес критерия разницы в эффективности лечения.")
                        .setParamDefaultValue("0.5")
                        .setParamInfo("")
                        .build());
                commonGroup.addParameter(new ParameterBuilder()
                        .setParamName("w2")
                        .setParamCaption("Вес критерия разницы мощности подгрупп")
                        .setParamType(ParameterType.DOUBLE)
                        .setParamHint("В формуле общего критерия разделения - " +
                                "вес критерия мощности подгрупп.")
                        .setParamDefaultValue("0.5")
                        .setParamInfo("")
                        .build());
                ParamsGroup bootstrapGroup = new ParamsGroup();
                bootstrapGroup.setGroup("BootstrapParams");
                bootstrapGroup.setCaption("Параметры бутстрэп-процедуры");
                bootstrapGroup.addParameter(new ParameterBuilder()
                        .setParamName("Bootstrap")
                        .setParamCaption("Включить выполнение бутстрэп-процедуры")
                        .setParamType(ParameterType.OPT)
                        .setParamHint("Включение или выключение бутстрэп-процедуры")
                        .setParamDefaultValue("True")
                        .setParamInfo("")
                        .build());
                bootstrapGroup.addParameter(new ParameterBuilder()
                        .setParamName("B")
                        .setParamCaption("Кол-во выборок в бутстрэп-процедуре")
                        .setParamType(ParameterType.INT)
                        .setParamHint("Количество выборок, используемых в бутстрэп-процедуре")
                        .setParamDefaultValue("25")
                        .setParamInfo("")
                        .build());
                paramsGroups.addParamsGroup(commonGroup);
                paramsGroups.addParamsGroup(bootstrapGroup);
                break;
            case "stima":
                ParamsGroup commonGroup1 = new ParamsGroup();
                commonGroup1.setGroup("Common");
                commonGroup1.setCaption("Общие");
                commonGroup1.addParameter(new ParameterBuilder()
                        .setParamName("maxsplit")
                        .setParamCaption("Макс. кол-во разделений")
                        .setParamType(ParameterType.INT)
                        .setParamHint("Максимальное количество разделений при построении дерева")
                        .setParamDefaultValue("")
                        .setParamInfo("")
                        .build());
                commonGroup1.addParameter(new ParameterBuilder()
                        .setParamName("first")
                        .setParamCaption("Номер переменной для первого разделения")
                        .setParamType(ParameterType.INT)
                        .setParamHint("По этой переменной произойдет первое разделение " +
                                "при построении дерева")
                        .setParamDefaultValue("")
                        .setParamInfo("")
                        .build());
                commonGroup1.addParameter(new ParameterBuilder()
                        .setParamName("vfold")
                        .setParamCaption("Кол-во подвыборок для кросс-валидации")
                        .setParamType(ParameterType.INT)
                        .setParamHint("Количество подвыборок, используемых при кросс-валидации")
                        .setParamDefaultValue("10")
                        .setParamInfo("")
                        .build());
                commonGroup1.addParameter(new ParameterBuilder()
                        .setParamName("cv")
                        .setParamCaption("Число прогонов кросс-валидации")
                        .setParamType(ParameterType.INT)
                        .setParamHint("Количество раз выполнения процедуры кросс-валидации")
                        .setParamDefaultValue("10")
                        .setParamInfo("")
                        .build());
                commonGroup1.addParameter(new ParameterBuilder()
                        .setParamName("minbucket")
                        .setParamCaption("Мин. кол-во объектов в листе")
                        .setParamType(ParameterType.INT)
                        .setParamHint("Минимальное допустимое количество объектов " +
                                "в одной подгруппе (листе дерева)")
                        .setParamDefaultValue("")
                        .setParamInfo("")
                        .build());
                commonGroup1.addParameter(new ParameterBuilder()
                        .setParamName("mincrit")
                        .setParamCaption("Мин. отклонение")
                        .setParamType(ParameterType.INT)
                        .setParamHint("Минимальное допустимое значение отклонения в новом листе, " +
                                "при котором построение дерева прекращается")
                        .setParamDefaultValue("")
                        .setParamInfo("")
                        .build());
                commonGroup1.addParameter(new ParameterBuilder()
                        .setParamName("pruning")
                        .setParamCaption("Включить выполнение прюнинг-процедуры")
                        .setParamType(ParameterType.OPT)
                        .setParamHint("Включение или выключение прюнинг-процедуры " +
                                "(отсечения лишних вариантов)")
                        .setParamDefaultValue("False")
                        .setParamInfo("")
                        .build());
                commonGroup1.addParameter(new ParameterBuilder()
                        .setParamName("c")
                        .setParamCaption("Параметр прюнинга")
                        .setParamType(ParameterType.DOUBLE)
                        .setParamHint("Значение параметра для правила прюнинга c*SE")
                        .setParamDefaultValue("")
                        .setParamInfo("")
                        .build());
                paramsGroups.addParamsGroup(commonGroup1);
                break;
            default:
                break;
        }
        return paramsGroups;
    }


    @RequestMapping(value = "{name}/input", method = RequestMethod.GET)
    public @ResponseBody
    FormatDescription getInputFormat(@PathVariable String name) {
        FormatDescription description = new FormatDescription();
        description.setName(name);
        switch (name) {
            case "quint":
                description.setDescription("JSON");
                break;
            case "stima":
                description.setDescription("JSON");
                break;
            default:
                description.setDescription("No such method implemented here");
                break;
        }

        return description;
    }

    @RequestMapping(value = "{name}/output", method = RequestMethod.GET)
    public @ResponseBody
    FormatDescription getOutputFormat(@PathVariable String name) {
        FormatDescription description = new FormatDescription();
        description.setName(name);
        switch (name) {
            case "quint":
                description.setDescription("JSON");
                break;
            case "stima":
                description.setDescription("JSON");
                break;
            default:
                description.setDescription("No such method implemented here");
                break;
        }

        return description;
    }

    @RequestMapping(value = "{name}/execute", method = RequestMethod.POST)
    public @ResponseBody Result getResult(@PathVariable String name) {
        Result result = new Result();
        switch (name) {
            case "quint":
                result.setResult("Qualitative Interaction Trees");
                break;
            case "stima":
                result.setResult("Simultaneous Threshold Interaction Modeling Algorithm");
                break;
            default:
                result.setResult("No such method implemented here");
                break;
        }
        return result;
    }
}
