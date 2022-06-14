Drop Table Medicine;

CREATE TABLE Medicine (
  name VARCHAR2(300) NOT NULL,
  image VARCHAR2(300) NOT NULL,
  character VARCHAR2(300) NOT NULL,
  effect VARCHAR2(300) NOT NULL,
  warning VARCHAR2(300) NOT NULL,
  company VARCHAR2(300) NOT NULL,
  expiration DATE NOT NULL,
  price NUMBER NOT NULL,
  stock NUMBER NOT NULL,
  PRIMARY KEY (name)
  );
  
INSERT INTO Medicine VALUES('tinunband', 'file:/C:/MedApp/src/img/tinunband.png', '살색 밴드', '티눈 제거', '당뇨병환자 사용 금지', '신신', to_date('2020/12/20', 'YYYY/MM/DD'), 3500, 5);
INSERT INTO Medicine VALUES('bepanthen', 'file:/C:/MedApp/src/img/bepanthen.png', '하얀색 연고', '상처 및 습진 치료', '눈 접촉 주의', '바이엘코리아', to_date('2020/12/21', 'YYYY/MM/DD'), 8000, 6);
INSERT INTO Medicine VALUES('hemaforce 정', 'file:/C:/MedApp/src/img/hemaforce.png', '장방형 갈색', '철 결핍 및 빈혈 예방', '알레르기 주의', '제이더블유중외제약제이더블유중외제약', to_date('2020/12/20', 'YYYY/MM/DD'), 20000, 7);
INSERT INTO Medicine VALUES('lindan', 'file:/C:/MedApp/src/img/lindan.png', '백색의 현탁성 로오숀제', '머리이 감염증', '임산부 주의', '신신', to_date('2020/12/24','YYYY/MM/DD'), 4000, 3);
INSERT INTO Medicine VALUES('dupi', 'file:/C:/MedApp/src/img/dupi.png', '액체', '가려움, 피부염', '1일 수회 사용', '한미', to_date('2020/12/31', 'YYYY/MM/DD'), 3500, 4);
INSERT INTO Medicine VALUES('lamisil', 'file:/C:/MedApp/src/img/lamisil.png', '백색의 크림제', '피부진균감염증피부진균감염증', '1일 1-2회, 소아 사용주의', '글락소스미스클라인컨슈머헬스케어코리아글락소스미스클라인컨슈머헬스케어코리아', to_date('2020/12/20', 'YYYY/MM/DD'), 8000, 3);
INSERT INTO Medicine VALUES('easycare', 'file:/C:/MedApp/src/img/easycare.png', '투명액체', '조갑진균증', '30초 건조 후 6시간 세척금지, 18세 미만 주의', '유한양행', to_date('2021/01/01', 'YYYY/MM/DD'), 10000, 10);
INSERT INTO Medicine VALUES('tylenol', 'file:/C:/MedApp/src/img/tylenol.png', '흰색의 정방형 필름코팅정', '해열, 진통, 소염제', '만12세 이상 1일 3회', '한국얀센', to_date('2021/02/20', 'YYYY/MM/DD'), 3000, 7);
INSERT INTO Medicine VALUES('aspirin', 'file:/C:/MedApp/src/img/aspirin.png', '흰색의 원형 필름코팅정', '혈전 생성 억제', '1일 1회 1정, 혈우병 환자 사용금지', '한미약품', to_date('2021/02/18', 'YYYY/MM/DD'), 5000, 7);
INSERT INTO Medicine VALUES('rexen', 'file:/C:/MedApp/src/img/rexen.png', '흰색의 포탄형 좌제', '치질 증상 완화', '환부가 화농되어있는 환자 사용 금지', '한림제약', to_date('2021/03/15', 'YYYY/MM/DD'), 20000, 4);
INSERT INTO Medicine VALUES('touchmed', 'file:/C:/MedApp/src/img/touchmed.png', '엷은 청색의 연고제', '구내염, 설염', '1일 2-4회 환부에 도포, 알레르기주의', '동화약품', to_date('2022/06/22', 'YYYY/MM/DD'), 5000, 4);
INSERT INTO Medicine VALUES('stomasin', 'file:/C:/MedApp/src/img/stomasin.png', '파랑의 장방형 경질캡슐', '편도염 완화', '1회 2캡슐, 유당불내증 환자 사용금지', '아이월드제약', to_date('2022/04/05', 'YYYY/MM/DD'), 8000, 3);
INSERT INTO Medicine VALUES('strepsils', 'file:/C:/MedApp/src/img/strepsils.png', '주황색의 원형 트로키정', '효과\\인후염 증상 완화', '1일 최대 5개 복용', '옥시레킷벤키저', to_date('2022/03/20', 'YYYY/MM/DD'), 8000, 6);
INSERT INTO Medicine VALUES('pollydent', 'file:/C:/MedApp/src/img/pollydent.png', '발포정', '틀니, 의치 세정, 방취효과', '삼키지 말 것', '글락소스미스클라인', to_date('2022/02/01', 'YYYY/MM/DD'), 25000, 8);
INSERT INTO Medicine VALUES('coq10', 'file:/C:/MedApp/src/img/coq10.png', '적색의 연질캡슐', '항산화와 혈압감소도움', '1일 캡슐', 'GNM', to_date('2022/09/10', 'YYYY/MM/DD'), 30000, 4);
INSERT INTO Medicine VALUES('champix', 'file:/C:/MedApp/src/img/champix.png', '백색의 장방형 필름코팅정백색의 장방형 필름코팅정', '금연치료의 보조요법금연치료의 보조요법', '자살경향성 주의자살경향성 주의', '화이자화이자', to_date('2021/12/20', 'YYYY/MM/DD'), 15000, 8);
INSERT INTO Medicine VALUES('tinun', 'file:/C:/MedApp/src/img/tinun.png', '투명액체', '티눈, 굳은살 제거', '2-5일 후 교체', '신신', to_date('2021/07/13', 'YYYY/MM/DD'), 2800, 3);
INSERT INTO Medicine VALUES('cleancle', 'file:/C:/MedApp/src/img/cleancle.png', '무색투명한 액', '관류, 점막세정, 객담배출 촉진', '주사제로 사용금지', '제이더블유생명과학', to_date('2023/02/17', 'YYYY/MM/DD'), 1700, 3);
INSERT INTO Medicine VALUES('brown juice', 'file:/C:/MedApp/src/img/juice.png', '생약의 냄새가나는 갈색의 액체', '허약체질, 피로회복, 과로', '고혈압환자 주의, 1일 3회', '해태에이치티비', to_date('2020/12/22', 'YYYY/MM/DD'), 3700, 4);
INSERT INTO Medicine VALUES('roidipen', 'file:/C:/MedApp/src/img/roidipen.png', '외형', '설사, 묽은 변, 체함, 토사', '15세 이상, 1회2캡슐 1일 2회', '미래제약', to_date('2020/12/23', 'YYYY/MM/DD'), 7000, 4);
INSERT INTO Medicine VALUES('stomaq', 'file:/C:/MedApp/src/img/stomaq.png', '황갈색의 과립제', '답답한 명치, 신경과민 증상 완화', '1일 3회, 고혈압 환자 주의', '경방신약', to_date('2020/12/23', 'YYYY/MM/DD'), 4000, 4);
INSERT INTO Medicine VALUES('buscopan', 'file:/C:/MedApp/src/img/buscopan.png', '백색의 타원형 필름코팅정', '담도계 기능장애 및 경련성 동통', '1일 3회, 술과 함께 복용시 간손상 유발', '사노피아벤티스코리아', to_date('2020/12/27', 'YYYY/MM/DD'), 7000, 6);
INSERT INTO Medicine VALUES('bomilong', 'file:/C:/MedApp/src/img/bomilong.png', '황색의 산제', '멀미에 의한 어지러움, 구토, 두통의 예방 및 완화', '성인: 1회 1포,소아: 1회 2/3포 ', '영일제약', to_date('2020/12/31', 'YYYY/MM/DD'), 5000, 1);
INSERT INTO Medicine VALUES('kimite', 'file:/C:/MedApp/src/img/kimite.png', '엷은 황갈색의 폴리에스테르필름소재의 원형 패취', '멀미에 의한 구역, 구토의 예방', '7세 이하 사용금지', '명문제약', to_date('2020/12/31', 'YYYY/MM/DD'), 18000, 7);
INSERT INTO Medicine VALUES('insadol', 'file:/C:/MedApp/src/img/insadol.png', '주황의 원형 필름코팅정', '치주치료 휴 치은염, 치주염의 보조치료치주치료 휴 치은염, 치주염의 보조치료', '유당불내증 환자 주의', '동국제약', to_date('2022/07/19', 'YYYY/MM/DD'), 21000, 8);
INSERT INTO Medicine VALUES('igatan', 'file:/C:/MedApp/src/img/igatan.png', '분홍색 가루가 든 노랑의 장방형 경질캡슐분홍색 가루가 든 노랑의 장방형 경질캡슐', '치주치료 휴 치은염, 치주염의 보조치료', '알레르기 주의', '명인제약', to_date('2022/06/09', 'YYYY/MM/DD'), 20000, 11);
INSERT INTO Medicine VALUES('rhamnople', 'file:/C:/MedApp/src/img/rhamnople.png', '흰 가루가 든 백색의 캡슐', '유산균충전', '1일 1회 1캡슐, 알레르기주의', '엘앤바이오랩', to_date('2021/03/16', 'YYYY/MM/DD'), 150000, 7);
INSERT INTO Medicine VALUES('bcomc', 'file:/C:/MedApp/src/img/bcomc.jpg', '분홍색의 타원형 필름코팅정', 'B1, B2, B6, C의 보급', '제산제 복용 시 복용금지', '1일 2회',to_date('2022/12/22', 'YYYY/MM/DD'), 28000, 20);
INSERT INTO Medicine VALUES('lemona', 'file:/C:/MedApp/src/img/lemona.jpg', '황색의 미립상 산제', 'B2, B6, C의 보급, 기미, 주근깨 완화', '1일 1회 1포', '경남제약주식회사', to_date('2021/01/20', 'YYYY/MM/DD'), 18000, 14);

CREATE TABLE userdata (
    name VARCHAR2(1000) NOT NULL,
    id VARCHAR2(1000) NOT NULL,
    password VARCHAR2(1000) NOT NULL,
    permit VARCHAR2(1000) NOT NULL,
    PRIMARY KEY (id));

INSERT INTO userdata(name, id, password, permit) Values ('관리자', 'manager', '0000', '관리자');

commit;