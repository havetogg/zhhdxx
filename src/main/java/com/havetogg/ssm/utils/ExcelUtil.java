package com.havetogg.ssm.utils;

import jxl.Workbook;
import jxl.format.*;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.*;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @Auther: Tinny.liang
 * @Description:
 * @Date: Create in 9:55 2017/5/24
 * @Modified By:
 */
public class ExcelUtil {
    public static <T> WritableWorkbook exportExcel(OutputStream os,List<T> eList) throws Exception {
        WritableWorkbook  wwb = Workbook.createWorkbook(os);
        WritableSheet ws = wwb.createSheet("Sheet1", 0);
        //去掉整个sheet中的网格线
        ws.getSettings().setShowGridLines(false);

        //    设置单元格的文字格式
        WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.NO_BOLD,
                false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
        WritableCellFormat wcf = new WritableCellFormat(wf);
        wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
        wcf.setAlignment(Alignment.CENTRE);
        wcf.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

        int i=0;
        for(T t:eList){
            Field[] field = t.getClass().getDeclaredFields();
            for (int j = 0; j < field.length; j++) {
                field[j].setAccessible(true);
                // 权限修饰符
                int mo = field[j].getModifiers();
                // 属性类型
                Class<?> type = field[j].getType();
                System.out.println(mo + " " + type.getName() + " " + field[j].getName() + ";"+field[j].get(t));
                ws.addCell(new Label(j,i,field[j].get(t).toString(),wcf));
            }
            i++;
        }
        return wwb;
    }
}
