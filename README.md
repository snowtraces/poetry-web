# poetry-web
一个唐宋诗词简单展示的web项目

## 技术构成
+ 基于java，框架spring-boot + springMVC + Mybatis
+ 数据库为Mysql
+ ~~web模板采用freeMarker，以及ajax加载~~
+ 页面由vue重构：https://github.com/snowtraces/poetry-vue ，原页面依旧保留

## 查询实现
### 1. mysql全文索引
模糊查询通过mysql建立fullText全文索引，未使用分词器，将最小分词数改为2：
```log
[mysqld]
innodb_ft_min_token_size=2
ft_min_word_len=2
```
### 2. 分词问题
不但mysql没有有效的中文分词器，而且针对古文/古诗的分词器根本就没有。只进行了简单的分词，保留2个字的结果，放入keyword属性列。

### 3. 查询速度
查询采用了boolean模糊匹配，效率虽然比直接模糊查询快，但有时也会很慢。将查询结果前100条id放入表中，每次查询先查结果表。

## 数据源
+ 唐宋诗词来源于：https://github.com/chinese-poetry/chinese-poetry
+ 本项目对应数据库下载(base64，更新时间2018/12/7)：```aHR0cDovL3NoaWNpZ2VmdS5uZXQvc2l0ZW1hcC9wb2V0cnktc3FsLnppcA==```

## 展示
+ 新页面：https://shicigefu.net/#/poetry/1391
+ 旧页面：https://shicigefu.net/poetry/1391
