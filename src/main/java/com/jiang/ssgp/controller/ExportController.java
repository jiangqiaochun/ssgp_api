package com.jiang.ssgp.controller;

import com.jiang.ssgp.domain.vo.SelectionVO;
import com.jiang.ssgp.service.SelectionService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
public class ExportController {
    private final SelectionService selectionService;

    public ExportController(SelectionService selectionService) {
        this.selectionService = selectionService;
    }

    @RequestMapping("/export")
    public void export(String meetingId, HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个Excel表单,参数为sheet的名字
        HSSFSheet sheet = workbook.createSheet("选题情况表");
        //创建表头
        setTitle(workbook, sheet);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER );
        //新增数据行，并且设置单元格数据
        List<SelectionVO> selectionVOList = selectionService.findAll();
        int rowNum = 2;
        for (SelectionVO selectionVO: selectionVOList) {
            HSSFRow row = sheet.createRow(rowNum);
            HSSFCell cell0 = row.createCell(0);
            cell0.setCellValue(selectionVO.getStudentName());
            cell0.setCellStyle(style);
            HSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(selectionVO.getStudentId());
            cell1.setCellStyle(style);
            HSSFCell cell2 = row.createCell(2);
            cell2.setCellValue(selectionVO.getStudentClassNum());
            cell2.setCellStyle(style);
            HSSFCell cell3 = row.createCell(3);
            cell3.setCellValue(selectionVO.getStudentPhoneNum());
            cell3.setCellStyle(style);
            HSSFCell cell4 = row.createCell(4);
            cell4.setCellValue(selectionVO.getProjectName());
            cell4.setCellStyle(style);
            HSSFCell cell5 = row.createCell(5);
            cell5.setCellValue(selectionVO.getProjectNature());
            cell5.setCellStyle(style);
            HSSFCell cell6 = row.createCell(6);
            cell6.setCellValue(selectionVO.getProjectType());
            cell6.setCellStyle(style);
            HSSFCell cell7 = row.createCell(7);
            cell7.setCellValue(selectionVO.getTeacherName());
            cell7.setCellStyle(style);
            HSSFCell cell8 = row.createCell(8);
            cell8.setCellValue(selectionVO.getTeacherJobTitle());
            cell8.setCellStyle(style);
            HSSFCell cell9 = row.createCell(9);
            cell9.setCellValue(selectionVO.getTeacherPhoneNum());
            cell9.setCellStyle(style);
            rowNum++;
        }

        String fileName = "select.xlsx";
        //清空response
        response.reset();
        //设置response的Header
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/vnd.ms-excel;charset=gb2312");
        //将excel写入到输出流中
        workbook.write(os);
        os.flush();
        os.close();
    }

    /***
     * 设置表头
     * @param workbook
     * @param sheet
     */
    private void setTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
        //设置为加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER );
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell=row.createCell(0);
        //设置表头
        cell.setCellValue( "选题情况");
        cell.setCellStyle(style);
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
//        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
//        sheet.setColumnWidth(0, 30 * 256);
//        sheet.setColumnWidth(1, 60 * 256);
        //设置第一行
        HSSFRow row1 = sheet.createRow(1);
        HSSFCell cell1;
        cell1 = row1.createCell(0);
        cell1.setCellValue("学生姓名");
        cell1.setCellStyle(style);
        cell1 = row1.createCell(1);
        cell1.setCellValue("学生学号");
        cell1.setCellStyle(style);
        cell1 = row1.createCell(2);
        cell1.setCellValue("学生班级");
        cell1.setCellStyle(style);
        cell1 = row1.createCell(3);
        cell1.setCellValue("学生电话");
        cell1.setCellStyle(style);
        cell1 = row1.createCell(4);
        cell1.setCellValue("选题名称");
        cell1.setCellStyle(style);
        cell1 = row1.createCell(5);
        cell1.setCellValue("选题性质");
        cell1.setCellStyle(style);
        cell1 = row1.createCell(6);
        cell1.setCellValue("选题类型");
        cell1.setCellStyle(style);
        cell1 = row1.createCell(7);
        cell1.setCellValue("老师姓名");
        cell1.setCellStyle(style);
        cell1 = row1.createCell(8);
        cell1.setCellValue("老师职称");
        cell1.setCellStyle(style);
        cell1 = row1.createCell(9);
        cell1.setCellValue("老师电话");
        cell1.setCellStyle(style);

    }
}
