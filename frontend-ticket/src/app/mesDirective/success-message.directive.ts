import { Directive, ElementRef, Input } from '@angular/core';

@Directive({
  selector: '[appSuccessMessage]',
  
})
  
export class SuccessMessageDirective {
  @Input() appSuccessMessage!: string;

  constructor(private el: ElementRef) {}

  ngOnInit() {
    this.el.nativeElement.textContent = this.appSuccessMessage;
    this.el.nativeElement.classList.add('success-message');
  }
}
