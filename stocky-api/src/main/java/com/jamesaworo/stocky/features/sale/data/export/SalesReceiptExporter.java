/*
 * @Author: james.junior
 * @Date: 7/25/23 14:10
 *
 * @Project: stocky-api
 */

package com.jamesaworo.stocky.features.sale.data.export;

import com.jamesaworo.stocky.core.params.DataExporter;
import com.jamesaworo.stocky.core.utils.ExportUtil;
import com.jamesaworo.stocky.features.sale.data.request.SaleReceiptItemRequest;
import com.jamesaworo.stocky.features.sale.domain.entity.SaleTransaction;
import com.jamesaworo.stocky.features.sale.domain.entity.SaleTransactionItem;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.jamesaworo.stocky.core.constants.ReportConstant.*;
import static com.jamesaworo.stocky.core.utils.Util.*;
import static com.jamesaworo.stocky.features.sale.data.enums.SaleReportFileEnum.SALE_RECEIPT;
import static java.lang.String.format;
import static org.springframework.util.ObjectUtils.isEmpty;

@Component
@RequiredArgsConstructor
public class SalesReceiptExporter implements DataExporter<byte[], SaleTransaction> {

    private final SaleExportCommon common;

    @Override
    public byte[] export(SaleTransaction data) {
        HashMap<String, Object> map = toMap(data);
        return ExportUtil.generatePDFBytes(map, SALE_RECEIPT.asInputStream(), new JREmptyDataSource());
    }

    private HashMap<String, Object> toMap(SaleTransaction transaction) {
        HashMap<String, Object> map = this.common.toMap();
        map.put("supportStaff", !isEmpty(transaction.getCreatedBy()) ? transaction.getCreatedBy().toUpperCase() : EMPTY);
        map.put("receiptCustomer", !isEmpty(transaction.getCustomer()) ? transaction.getCustomer().getFullNameAndPhone().toUpperCase() : EMPTY);
        map.put("receiptSerial", transaction.getSerial().toUpperCase());
        map.put("receiptDate", format("%s - %s", formatDate(transaction.getDate()), formatTime(transaction.getTime())).toUpperCase());
        map.put("receiptPaymentMethod", transaction.getPaymentOption().getTitle().toUpperCase());
        map.put("receiptDisclaimer", RECEIPT_DISCLAIMER);
        map.put("receiptItems", new JRBeanCollectionDataSource(getSalesReceiptItems(transaction)));
        map.put("amountVat", currency() + formatAmount(transaction.getAmount().getTaxTotal()));
        map.put("amountDiscount", currency() + formatAmount(transaction.getAmount().getDiscountTotal()));
        map.put("amountSubTotal", currency() + formatAmount(transaction.getAmount().getSubTotal()));
        map.put("amountTotal", currency() + formatAmount(transaction.getAmount().getGrandTotal()));
        map.put("receiptFooter", RECEIPT_FOOTER);
        return map;
    }

    private List<SaleReceiptItemRequest> getSalesReceiptItems(SaleTransaction transaction) {
        List<SaleReceiptItemRequest> list = new ArrayList<>();
        if (!isEmpty(transaction) && !isEmpty(transaction.getItems())) {
            for (SaleTransactionItem item : transaction.getItems()) {
                SaleReceiptItemRequest receiptItem = new SaleReceiptItemRequest();
                receiptItem.setName(format("%s. %s @ %s",
                        item.getQuantity().toString(),
                        item.getProduct().getBasic().getProductName(),
                        currency() + formatAmount(item.getPrice()))
                );
                receiptItem.setPrice(formatAmount(item.getSubTotal()));
                list.add(receiptItem);
            }
        }
        return list;
    }

    private String currency() {
        return this.common.currency();
    }
}