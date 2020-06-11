import { Component, OnInit, ViewChild, AfterViewInit, Inject } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import { FormBuilder, Validators } from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';

import { HistoryService, History} from '../rest/history.service';

export interface DialogData{
  text : '';
}

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {


  calculateForm = this.fb.group({
    fromLevel:[''],
    toLevel: ['']
  })

  blob;

  downloadForm = this.fb.group({
    rank:['']
  })

  displayedColumns: string[] = ['rank', 'from', 'to', 'experience'];
  dataSource;
  weather:string;
  records: History[] = [];

  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;


  tabSelectionChanged(event){ 
    this.getHistoricalRecords();
  }

  constructor(private fb:FormBuilder, private dfb:FormBuilder, private rest:HistoryService,
    private _snackBar:MatSnackBar, public historyData:History) { }

  ngOnInit(): void {
    this.getHistoricalRecords();
    
  }

  getHistoricalRecords(){
    this.rest.getAllHistory().subscribe(
      response => {
        this.dataSource = new MatTableDataSource(response);
        this.dataSource.paginator = this.paginator;
      }
    )
  }

  calculate(){
    this.rest.calculateExperience(this.calculateForm.value).subscribe(
      response => {
        this._snackBar.open(response, 'Calculation Results',{duration: 6000, verticalPosition: 'top'})
      }
    )
  }

  download(){
    this.rest.downloadReport(this.downloadForm.value).subscribe(
      response =>{
        if(response){
          this.blob = new Blob([response], {type: 'application/pdf'});

          var downloadURL = window.URL.createObjectURL(response);
          var link = document.createElement('a');
          link.href = downloadURL;
          link.download = "Generated Report.pdf";
          link.click();
        } else {
          this._snackBar.open('No data found for rank', 'Report Results', {duration: 6000, verticalPosition: 'top'})
        }
      }
    )
    
  }

}


@Component({
  selector: 'dialog',
  templateUrl: './dialog.html',
})
export class DialogDataDialog {
  constructor(@Inject(MAT_DIALOG_DATA) public data: DialogData) {}
}

