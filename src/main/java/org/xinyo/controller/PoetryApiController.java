package org.xinyo.controller;

import com.hankcs.hanlp.HanLP;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xinyo.domain.Author;
import org.xinyo.domain.Poetry;
import org.xinyo.domain.PoetryBean;
import org.xinyo.domain.TagRelation;
import org.xinyo.service.AuthorService;
import org.xinyo.service.PoetryService;
import org.xinyo.service.SearchResultService;
import org.xinyo.service.TagRelationService;
import org.xinyo.util.FileUtil;
import org.xinyo.util.JsonUtils;
import org.xinyo.util.UnicodeUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.xinyo.util.PoetryUtils.poetry2PoetryBean;

/**
 * Created by chengxinyong on 2018/3/27.
 */
@RestController
public class PoetryApiController {

    @Autowired
    private PoetryService poetryService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private SearchResultService searchResultService;

    @Autowired
    private TagRelationService tagRelationService;

    @RequestMapping(value = "/api/poetry/{id}", method = RequestMethod.GET)
    public Map<String, Object> getPoetryById(@PathVariable Integer id, @RequestParam Integer language) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("language", language);

        Poetry poetry = poetryService.findByIdAndLanguage(params);

        if (poetry == null) {
            poetry = new Poetry(id);
        }

        PoetryBean poetryBean = poetry2PoetryBean(poetry);
        if (poetry.getAuthorId() != null) {
            params.put("id", poetry.getAuthorId());
            Author author = authorService.findByIdAndLanguage(params);
            resultMap.put("author", author);
        }

//        createKeywords(id);
//        new Thread(() -> replaceWenHao()).start();
//        new Thread(() -> createTags(id)).start();
//        new Thread(() -> createTagMapping(id)).start();

        resultMap.put("poetry", poetryBean);
        return resultMap;
    }

    @RequestMapping(value = "/api/poetry/search", method = RequestMethod.GET)
    public Map getPoetryByKeyword(@RequestParam String keyword,
                                  @RequestParam(name = "page", defaultValue = "1") Integer page,
                                  @RequestParam(name = "language", defaultValue = "1") Integer language) {
        Map<String, Object> resultMap = new HashMap<>();

        page = page == null?1:page;
        keyword = UnicodeUtils.transBaseUnicode(keyword);
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);
        params.put("page", page);
        params.put("language", language);

        Map<String, Object> map = searchResultService.searchByKeyword(params);

        List<PoetryBean> poetryBeanList = poetry2PoetryBean((List<Poetry>) map.get("data"));

        resultMap.put("poetryBeanList", poetryBeanList);
        resultMap.put("keyword", language == 0 ? HanLP.convertToTraditionalChinese(keyword) : keyword);
        resultMap.put("page", page);
        resultMap.put("total", map.get("total"));
        if (map.get("relationTag") != null) {
            resultMap.put("relationTag",map.get("relationTag"));
        }
        if (map.get("author") != null) {
            resultMap.put("author", map.get("author"));
        }

        return resultMap;
    }


    /**
     * 生成tag中的关联关系
     * @param id
     */
    private void createTagMapping(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("language", 1);
        StringBuilder sb = new StringBuilder();
        int globalId = 1;
        File file = new File("D:/tagMapping.txt");
        int step = 2000;
        for (int i = 1; i <= 335181; i += step) {
            params.put("id", i);
            List<Poetry> poetryList = poetryService.find1000ById(i);
            for (Poetry poetry : poetryList) {
                String tags = poetry.getKeywords();
                // 获取tags列表
                List tagList;
                if (StringUtils.isNoneEmpty(tags)) {
                    tagList = JsonUtils.jsonToList(tags);
                } else {
                    continue;
                }

                for (int j = 0; j < tagList.size(); j++) {
                    String tagA = (String) tagList.get(j);
                    for (int k = j+1; k < tagList.size(); k++) {
                        String tagB = (String) tagList.get(k);

                        // 直接写入，后续结果再进行合并
                        sb.append(globalId++).append(",");
                        if (tagA.compareTo(tagB) > 0) {
                            sb.append("\"").append(tagB).append("\"").append(",");
                            sb.append("\"").append(tagA).append("\"").append(",");
                            sb.append(1).append(",").append("\n");
                        } else {
                            sb.append("\"").append(tagA).append("\"").append(",");
                            sb.append("\"").append(tagB).append("\"").append(",");
                            sb.append(1).append("\n");
                        }

                        if (globalId % 5000 == 0) {
                            FileUtil.writer(file, sb.toString(), true);
                            sb = new StringBuilder();
                        }
                    }
                }
            }
            System.out.println("do id: " + i);
        }
        FileUtil.writer("tagMapping.txt", sb.toString(), true);
        sb = new StringBuilder();
        System.err.println("DONE!!!!!!!!");

    }


    /**
     * 自动生成标签
     */
    private void createTags(int id) {
        Map<String, Object> params = new HashMap<>();
        String yuefuName = "(郊庙歌辞|燕射歌辞|鼓吹曲辞|横吹曲辞|相和歌辞|清商曲辞|舞曲歌辞|琴曲歌辞|杂曲歌辞|近代曲辞|杂歌谣辞|新乐府辞)";
        params.put("language", 1);
        for (int i = 1; i <= 332875; i++) {
            params.put("id", i);
            Poetry poetry = poetryService.findByIdAndLanguage(params);
            if(poetry == null) continue;
            String title = poetry.getTitle();
            String tags = poetry.getTags();
            List tagList;
            if(tags == null || tags.equals("")) {
                tagList = new ArrayList();
            } else {
                tagList = JsonUtils.jsonToList(tags);
            }

            if (title.matches(yuefuName + ".*")) {
                // 匹配到乐府标题
                String tagName = title.replaceAll(yuefuName + ".*", "$1");

                boolean flag = false;
                if (!tagList.contains(tagName)) {
                    tagList.add(0, tagName);
                    flag = true;
                }
                if (!tagList.contains("乐府")) {
                    tagList.add(0, "乐府");
                    flag = true;
                }

                if (flag) {
                    params.put("tags", JsonUtils.toJson(tagList));
                    int result = poetryService.updateTagsById(params);
                    if (result > 0) {
                        System.err.println(params);
                    }
                }
            }else {
                System.out.println("...");
            }
        }
    }

    /**
     * 自动生成关键词
     *
     * @param id
     */
    private void createKeywords(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("language", 1);
        for (int i = 332876; i <= 333180; i++) {
            params.put("id", i);
            Poetry poetry = poetryService.findByIdAndLanguage(params);
            String s = poetry.getTitle() + ", " + poetry.getParagraphs();
            List<String> strings = HanLP.extractKeyword(s, 32);
            List<String> list = new ArrayList<>();
            for (String string : strings) {
                if (string.length() == 2) {
                    list.add(string);
                }
            }

            String json = JsonUtils.toJson(list);

            params.put("keywords", json);
            poetryService.updateKeywordsById(params);
            System.err.println(i);
        }

    }

    /**
     * 字符集不支持，乱码4个问好替换
     */
    private void replaceWenHao() {

        // 1. 查询简体结果
        for (int i : wenhaoId) {
            Map<String, Object> params = new HashMap<>();
            params.put("id", i);
            params.put("language", 1);

//            Poetry sp = poetryService.findByIdAndLanguage(params);
            Author sp = authorService.findByIdAndLanguage(params);

            params.put("language", 0);
            Author tr = authorService.findByIdAndLanguage(params);

            String spStr = sp.getDesc();
            String trStr = tr.getDesc();

            String spStrResult = getReplaced(spStr, trStr);


            params.put("title", spStrResult);
            System.out.println(i + ": " + spStrResult);
            authorService.updateDescSpById(params);
        }

    }

    private String getReplaced(String source, String base) {
        int wenhaoIndex = source.indexOf("????", 0);
        if (wenhaoIndex != -1) {
            String targetStr = base.substring(wenhaoIndex, wenhaoIndex + 1);
            String spStrResult = source.replaceFirst("[?]{4}", targetStr);
            return getReplaced(spStrResult, base);
        } else {
            return source;
        }
    }


    private static int[] wenhaoId = new int[]{};


}
