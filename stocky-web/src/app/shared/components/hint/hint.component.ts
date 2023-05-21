import {Component, Input} from '@angular/core';

@Component({
    selector: 'app-hint',
    templateUrl: './hint.component.html',
    styles: [],
})
export class HintComponent {
    @Input()
    public props?: {title?: string; content: any};

    @Input()
    public content?: string;
}
