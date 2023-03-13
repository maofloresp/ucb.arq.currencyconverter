export interface Conversion
{
    id?: number;
    date: string;
    currencyFrom: string;
    currencyTo: string;
    amount: number;
    result: number;
}
