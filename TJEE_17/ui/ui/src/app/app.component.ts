import { Component, OnInit } from '@angular/core';
import { UntypedFormControl, Validators } from '@angular/forms';
import { AppService } from './app.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'ui';

  displayedColumns: string[] = ['position', 'createdDate', 'hash', 'action'];
  inputFC: UntypedFormControl;
  generatedHash = '';
  hashAlreadySaved = false;
  dataSource: any[] = [];
  
  constructor(private appService: AppService,
    private snackBar: MatSnackBar) {
    this.inputFC = new UntypedFormControl('', [Validators.required]);
  }

  ngOnInit(): void {
    this.getHashList();
  }

  generateHash() {
    this.appService.generateHash(this.inputFC.value).subscribe(hash => {
      this.generatedHash = hash;
      this.openSnackBar(`Hash ${this.generatedHash} successfully generated`);
      this.hashAlreadySaved = false;
    });
  }

  saveHash() {
    this.appService.saveHash(this.generatedHash).subscribe(() => {
      this.getHashList();
      this.openSnackBar(`Hash ${this.generatedHash} successfully saved`);
      this.hashAlreadySaved = true;
    });
  }

  deleteHash(hash: string) {
    this.appService.deleteHash(hash).subscribe(() => {
      this.getHashList();
      this.openSnackBar(`Hash ${hash} successfully deleted`);
    });
  }

  getHashList() {
    this.appService.getHashList().subscribe(hashList => {
      this.dataSource = hashList.map((hash: any) => {
        hash.createdDate = new Date(hash.createdDate).toLocaleString();
        return hash;
      });
    })
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, '', {
      duration: 3000,
      horizontalPosition: 'center'
    });
  }
}
