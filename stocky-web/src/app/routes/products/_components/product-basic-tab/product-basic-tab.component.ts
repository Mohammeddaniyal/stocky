import {Component, Input} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {ProductPopover} from '../../../../data/constant/message.constant';
import {getFormGroupFromParent, getNzFormControlValidStatus} from '../../../../shared/utils/util';

@Component({
    selector: 'app-product-basic-tab',
    templateUrl: './product-basic-tab.component.html',
    styles: [
        `
          :host ::ng-deep div[role="alert"] {
            margin-bottom: 25px;
          }
        `
    ]
})
export class ProductBasicTabComponent {
    public popover = ProductPopover;

    @Input()
    public formGroup?: FormGroup;

    public valid(name: string) {
        return getNzFormControlValidStatus(name, this.getForm());
    }

    public getForm() {
        return getFormGroupFromParent(this.formGroup!, 'basic');
    }


}
