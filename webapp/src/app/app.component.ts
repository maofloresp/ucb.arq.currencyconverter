import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

interface Conversion {
  date: string;
  from: string;
  to: string;
  amount: number;
  value: number;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'Currency Converter';
  author = 'Mauricio Flores';
  currencyForm : FormGroup;

  displayedColumns: string[] = ['date', 'from', 'to', 'amount', 'value'];
  dataSource =  [
    {date: '2023-03-11', from: 'EUR', to: 'USD', amount: 778.03, value: 853.12},
  ];
  /**
   *
   */
  constructor(private formBuilder: FormBuilder)
  {
    this.currencyForm = this.formBuilder.group({
      from: '',
      to: '',
      amount: ''
    })
    
  }

  submit()
  {
    console.log(this.currencyForm.value)
  }
}
