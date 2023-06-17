import {Component, OnInit} from '@angular/core';
import {FormGroup, UntypedFormBuilder, Validators} from '@angular/forms';
import {NzNotificationService} from 'ng-zorro-antd/notification';
import {handleUsecaseRequest, markFormFieldsAsDirtyAndTouched} from '../../../../shared/utils/util';
import {PaymentOptionUsecase} from '../../_usecase/payment-option.usecase';

@Component({
    selector: 'app-company-payment-option-form',
    templateUrl: './company-payment-option-form.component.html',
    styles: []
})
export class CompanyPaymentOptionFormComponent implements OnInit {

    public form!: FormGroup;
    public isSaving = false;

    constructor(
        private fb: UntypedFormBuilder,
        private usecase: PaymentOptionUsecase,
        private notification: NzNotificationService
    ) {}

    public ngOnInit() {
        this.initForm();
    }

    public async onCreate(): Promise<void> {
        if (this.form.invalid) {
            markFormFieldsAsDirtyAndTouched(this.form);
            return;
        }
        const option = this.form.value;
        await handleUsecaseRequest(this.usecase.save(option), this.notification);
        this.onResetPayload();
    }

    private initForm() {
        this.form = this.fb.group({
            title: [null, [Validators.required]],
            description: []
        });
    }

    private onResetPayload() {
        this.initForm();
        this.isSaving = false;
        this.usecase.setTrigger(true);
    }


}
