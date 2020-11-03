
package com.carel.persistence.common;

/**
 * Description:
 * @author Matthew Xu
 * @date Sep 17, 2020
 */
public enum ResultCode {

	SUCCESS(200),
    FAIL(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    private final int status;

    ResultCode(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
