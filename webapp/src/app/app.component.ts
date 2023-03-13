import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Conversion } from './models/conversion'
import { ConversionService } from './services/conversion.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'Currency Converter';
  author = 'Mauricio Flores';
  currencyForm : FormGroup;
  result = 0;

  displayedColumns: string[] = ['date', 'from', 'amount', 'to', 'value'];
  dataSource: Conversion[] =  [];
  /**
   *
   */
  constructor(private formBuilder: FormBuilder, private conversionService: ConversionService)
  {
    this.currencyForm = this.formBuilder.group({
      from: '',
      to: '',
      amount: ''
    })

    this.readConversions();
  }

  private getFormValue(fieldName: string): string
  {
    return this.currencyForm.get(fieldName)?.value;
  }

  private readConversions(page : number = 1)
  {
    this.conversionService.getConversions(page).subscribe({
      next: (data) => this.dataSource = data,
      error: () => this.dataSource = []
    });
  }

  private convert(from: string, to: string, amount: number) : void
  {
    this.conversionService.postConversion(from,to,amount).subscribe({
      next: (data) => 
      {
        this.result = data.result;
        this.readConversions();
      },
      error: () => this.result = -1
    });
  }

  submit()
  {    
    this.convert(this.getFormValue('from'), this.getFormValue('to'), parseInt(this.getFormValue('amount')));
  }


}
