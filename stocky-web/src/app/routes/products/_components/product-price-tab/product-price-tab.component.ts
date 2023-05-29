import {Component, Input} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {PRODUCT_CREATE_POPOVER} from '../../../../data/constant/message.constant';
import {getFormGroupFromParent} from '../../../../shared/utils/util';

@Component({
    selector: 'app-product-price-tab',
    templateUrl: './product-price-tab.component.html',
    styles: []
})
export class ProductPriceTabComponent {
    public popover = PRODUCT_CREATE_POPOVER;

    @Input()
    public formGroup?: FormGroup;
    protected readonly getSubFormGroup = getFormGroupFromParent;

    public getForm(): FormGroup<any> {
        return getFormGroupFromParent(this.formGroup!, 'price');
    }

    public calculateSellingPrice() {
        if (this.formGroup) {
            const form = this.getForm();
            const costPrice = form.get('costPrice')?.value;
            const markupPercent = form.get('markup')?.value;

            const markupAmount = Number(costPrice) * Number((markupPercent / 100));
            const sellingPrice = Number(costPrice) + Number(markupAmount);

            form.get('sellingPrice')?.setValue(sellingPrice);
        }
    }
}
