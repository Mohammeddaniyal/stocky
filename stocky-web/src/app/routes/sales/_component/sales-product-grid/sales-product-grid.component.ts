import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {NzMessageService} from 'ng-zorro-antd/message';
import {Subscription} from 'rxjs';
import {PageResultPayload} from '../../../../data/payload/common.interface';
import {PagePayload} from '../../../../data/payload/common.payload';
import {getProductFullName, getProductName} from '../../../../shared/utils/util';
import {ProductPayload} from '../../../products/_data/product.payload';
import {SaleCart} from '../../_data/sale-cart.payload';
import {SaleCartUsecase} from '../../_usecase/sale-cart.usecase';
import {SaleProductsUsecase} from '../../_usecase/sale-products.usecase';

@Component({
    selector: 'app-sales-product-grid',
    templateUrl: './sales-product-grid.component.html',
    styles: [
        `
          .grid-wrapper {
            cursor: pointer;
          }

          .grid {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            background-color: #1890ff;
            height: 120px;
            font-size: 16px;
            line-height: 20px;
            border-radius: 4px;
            text-align: center;
            color: #ffffff;
            padding-left: 5px;
            padding-right: 5px;
            padding-top: 3px;
            word-wrap: break-word;
          }

        `
    ]
})
export class SalesProductGridComponent implements OnInit, OnDestroy {

    @Input()
    public products?: Array<ProductPayload> = [];

    public cart?: SaleCart;

    @Input()
    public page?: PagePayload = new PagePayload();
    protected readonly Array = Array;
    private sub = new Subscription();

    constructor(
        private saleProductUsecase: SaleProductsUsecase,
        private saleCartUsecase: SaleCartUsecase,
        private msg: NzMessageService
    ) {}


    public ngOnInit(): void {
        this.sub.add(
            this.saleCartUsecase.cart$.subscribe(value => this.cart = value)
        );
        this.sub.add(
            this.saleProductUsecase.searchResult$.subscribe(value => this.updateProductList(value))
        );
    }

    public ngOnDestroy() {
        this.sub.unsubscribe();
    }

    public getProductTitle(product: ProductPayload): string {
        return getProductFullName(product);
    }

    public isLowQuantity(product: ProductPayload): boolean {
        const basic = product?.basic;
        if (basic && basic?.quantity) {
            return basic?.quantity <= basic?.lowStockPoint;
        }
        return true;
    }

    public onAddProductToCart(product: ProductPayload): void {
        if (this.cart) {
            const isAddedToCart = this.cart.addItem(product);
            this.notify(isAddedToCart, product);
        }
    }

    private updateProductList(response: PageResultPayload<ProductPayload> | null): void {
        if (response) {
            this.page = response.page;
            this.products = [...response.result ?? []];
        }
    }

    private notify(isAdded: boolean, product: ProductPayload) {
        if (isAdded) {
            this.msg.info(getProductName(product) + ' Added to cart!');
        } else {
            this.msg.error(getProductName(product) + ' Already in cart!');
        }
    }
}